VERSION 5.00
Begin VB.Form frmMain 
   Caption         =   "Form1"
   ClientHeight    =   1260
   ClientLeft      =   165
   ClientTop       =   450
   ClientWidth     =   2595
   Icon            =   "frmMain.frx":0000
   LinkTopic       =   "Form1"
   ScaleHeight     =   84
   ScaleMode       =   0  'User
   ScaleWidth      =   173
   StartUpPosition =   3  '窗口缺省
   Begin VB.Timer timerDBconnect 
      Interval        =   60000
      Left            =   240
      Top             =   360
   End
   Begin VB.Timer timeGetSendData 
      Interval        =   6000
      Left            =   1560
      Top             =   360
   End
   Begin VB.Menu popMnuTray 
      Caption         =   "弹出菜单"
      Visible         =   0   'False
      Begin VB.Menu mnuConfigSysInfo 
         Caption         =   "配置信息"
      End
      Begin VB.Menu mnuLine1 
         Caption         =   "-"
      End
      Begin VB.Menu mnuStartSmsServer 
         Caption         =   "启动短信设备"
      End
      Begin VB.Menu mnuCloseSmsServer 
         Caption         =   "关闭短信设备"
      End
      Begin VB.Menu mnuLine2 
         Caption         =   "-"
      End
      Begin VB.Menu mnuExit 
         Caption         =   "退出系统"
      End
   End
End
Attribute VB_Name = "frmMain"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Option Explicit

Private Declare Function Shell_NotifyIcon Lib "shell32.dll" Alias "Shell_NotifyIconA" (ByVal dwMessage As Long, lpData As NOTIFYICONDATA) As Long

Private Declare Function SendMessage Lib "user32" Alias "SendMessageA" (ByVal hwnd As Long, ByVal wMsg As Long, ByVal wParam As Long, lParam As Any) As Long

Private Const WM_SYSCOMMAND = &H112
Private Const SC_RESTORE = &HF120&

Private LastState As Integer            '保留原窗口状态

'=====dwMessage可以是以下NIM_ADD、NIM_DELETE、NIM_MODIFY 标识符之一======

Private Const NIM_ADD = &H0             '在任务栏中增加一个图标
Private Const NIM_DELETE = &H2          '删除任务栏中的一个图标
Private Const NIM_MODIFY = &H1          '修改任务栏中个图标信息

Private Const NIF_MESSAGE = &H1         'NOTIFYICONDATA结构中uFlags的控制信息
Private Const NIF_ICON = &H2
Private Const NIF_TIP = &H4

Private Const WM_MOUSEMOVE = &H200      '在图标上移动鼠标
Private Const WM_LBUTTONDOWN = &H201    '鼠标左键按下
Private Const WM_LBUTTONUP = &H202      '鼠标左键释放
Private Const WM_LBUTTONDBLCLK = &H203  '双击鼠标左键
Private Const WM_RBUTTONDOWN = &H204    '鼠标右键按下
Private Const WM_RBUTTONUP = &H205      '鼠标右键释放
Private Const WM_RBUTTONDBLCLK = &H206  '双击鼠标右键
Private Const WM_SETHOTKEY = &H32       '响应您定义的热键

Private Type NOTIFYICONDATA
  
    cbSize As Long                         '该数据结构的大小
    hwnd As Long                           '处理任务栏中图标的窗口句柄
    uID As Long                            '定义的任务栏中图标的标识
    uFlags As Long                         '任务栏图标功能控制，可以是以下值的组合（一般全包括）
                                           'NIF_MESSAGE 表示发送控制消息；
                                           'NIF_ICON表示显示控制栏中的图标；
                                           'NIF_TIP表示任务栏中的图标有动态提示。
    uCallbackMessage As Long               '任务栏图标通过它与用户程序交换消息，处理该消息的窗口由hWnd决定
    hIcon As Long                          '任务栏中的图标的控制句柄
    szTip As String * 64                   '图标的提示信息
End Type

Dim myData As NOTIFYICONDATA
 

'隐藏主窗口
Private Sub cmdHide_Click()
    Me.Visible = False
End Sub

Private Sub Form_Load()
    
    '判断显示模式
    If WindowState = vbMinimized Then
      LastState = vbNormal
    Else
      LastState = WindowState
    End If
    
    '用于在托盘的图标上显示的x值
    Me.ScaleMode = vbPixels
    
    '设置托盘参数
    With myData
      .cbSize = Len(myData)
      .hwnd = Me.hwnd
      .uID = 0
      .uFlags = NIF_ICON Or NIF_MESSAGE Or NIF_TIP
      .uCallbackMessage = WM_MOUSEMOVE
      .hIcon = Me.Icon.Handle                             '默认为窗口图标
      .szTip = "短信发送服务器" & vbNullChar
    End With
    
    Shell_NotifyIcon NIM_ADD, myData
    Me.Visible = False
    
End Sub


Private Sub Form_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
   
    Select Case CLng(X)
    Case WM_RBUTTONUP                                  '鼠标在图标上右击时弹出菜单
    
        Me.PopupMenu popMnuTray
        
    Case WM_LBUTTONUP                                  '鼠标在图标上左击时窗口 显示信息
    
          Me.Visible = False
          frmInfo.Show
         
    Case WM_LBUTTONDBLCLK                              '双击鼠标左键
                
    Case WM_MOUSEMOVE
    
         'Me.Visible = False
         'frmInfo.Show
         
    End Select
  
End Sub

'关闭服务器
Private Sub mnuCloseSmsServer_Click()
    Call closeSMS
End Sub

'配置服务器
Private Sub mnuConfigSysInfo_Click()
    frmConfig.Show
End Sub

'退出系统
Private Sub mnuExit_Click()
   Shell_NotifyIcon NIM_DELETE, myData
   Call ForceClose
   End
End Sub

'开启服务器
Private Sub mnuStartSmsServer_Click()
    If Not gSMSModelOpenState Then
        Call openSMS
    End If
End Sub

'设置启动
Public Sub startApp()
    
     '屏蔽错误
    On Error Resume Next
    
    '判断显示模式
    If WindowState = vbMinimized Then
      LastState = vbNormal
    Else
      LastState = WindowState
    End If
    
    '用于在托盘的图标上显示的x值
    Me.ScaleMode = vbPixels
    
    '设置托盘参数
    With myData
      .cbSize = Len(myData)
      .hwnd = Me.hwnd
      .uID = 0
      .uFlags = NIF_ICON Or NIF_MESSAGE Or NIF_TIP
      .uCallbackMessage = WM_MOUSEMOVE
      .hIcon = Me.Icon.Handle                             '默认为窗口图标
      .szTip = "短信发送服务器" & vbNullChar
    End With
    
    Shell_NotifyIcon NIM_ADD, myData
    Me.Visible = False
    
    Call startSMS
End Sub

'开启设备
Public Sub startSMS()
     '是否和数据库连接
    If gConnectionState Then
       Call openSMS
    End If
   
End Sub

'----------*********** 短信操作 **********-------
'关闭模块
Private Sub closeSMS()

    Dim Val As Integer
    Val = CloseComm(CLng(gComPort))
    
    If Val = 0 Then
       gSMSModelOpenState = False
       gSysState = mdlMain.gSMSState_connection_close
     Else
       MsgBox "close fail"
     End If
     
End Sub

'开启模块
Private Sub openSMS()
    
     '屏蔽错误
    On Error Resume Next
    
    Dim Val As Long
    
    If gSMSModelOpenState Then Exit Sub
    
    Val = OpenComm(CLng(gComPort))
    If Val = 0 Then
        gSMSModelOpenState = True
        gSysState = mdlMain.gSMSState_connection_success
        gSMSModelSendState = False
     Else
        gSysState = mdlMain.gSMSState_connection_failer
        gSMSModelOpenState = False
     End If
     
End Sub

'读取短信中心号码
Private Function GetSMSSCA() As String
    
     '屏蔽错误
    On Error Resume Next
    
    If Not gSMSModelOpenState Then Exit Function
    
    If gSMSModelSendState Then Exit Function
    
    gSMSModelSendState = True
    
    Dim SCA As String
    SCA = String(255, " ")
    
    If GetSCA(CLng(gComPort), SCA) Then
    
        If SCA <> " " Then
        
             gSCA = Mid(SCA, 1, 14)
             Call mdlMain.saveConfigValue
             gSMSModelOpenState = True
            gSysState = mdlMain.gSMSState_connection_success
            GetSMSSCA = SCA
            
        Else
           GetSMSSCA = ""
        End If
        
    Else
        gSysState = mdlMain.gSMSState_connection_failer
        gSMSModelOpenState = False
    End If
    
    gSMSModelSendState = False
    
End Function

'设置短信中心号码
Private Sub SetSMSSCA()
     
     '屏蔽错误
    On Error Resume Next
    
    If Not gSMSModelOpenState Then Exit Sub
    
    If gSMSModelSendState Then Exit Sub
    
    gSMSModelSendState = True

    If SetSCA(CLng(gComPort), gSCA) Then

        gSMSModelOpenState = True
        gSysState = mdlMain.gSMSState_connection_success
        
    Else
        gSysState = mdlMain.gSMSState_connection_failer
        gSMSModelOpenState = False
    End If
    
     gSMSModelSendState = False
End Sub


'短信发送
Private Function smsSend(ByVal smsContext, ByVal smsNo)
     
     '屏蔽错误
    On Error GoTo ExecuteError
    
    If Not gSMSModelOpenState Then Exit Function
    
     If gSMSModelSendState Then Exit Function
    
    gSMSModelSendState = True
    
    If smsNo <> "" Then
        If SendMsg(CLng(gComPort), smsContext, smsNo, 1, True) = 0 Then
            smsSend = True
        Else
            smsSend = False
        End If
    Else
         smsSend = False
    End If
    
    gSMSModelSendState = False
    Exit Function
ExecuteError:
    Debug.Print "错误信息:" & Err.Description
        
End Function

'强制关闭
Private Sub ForceClose()
   
    If Not gSMSModelOpenState Then Exit Sub
    
    ForceCloseComm (CLng(gComPort))
    
End Sub

'强制关闭
Private Sub Form_Unload(Cancel As Integer)

    If Not gSMSModelOpenState Then Exit Sub
    ForceCloseComm (CLng(gComPort))
    
End Sub

'读取短信失败
Private Sub ReadFailedMsg()
     '屏蔽错误
    On Error GoTo ExecuteError
    
    If Not gSMSModelOpenState Then Exit Sub
    
    If gSMSModelSendState Then Exit Sub
    
    gSMSModelSendState = True
    
    Dim FailedMsg As String
    FailedMsg = String(255, " ")
    If GetFailedMsg(CLng(gComPort), FailedMsg) Then
        If FailedMsg <> "" Then
            gSysState = mdlMain.gSMSState_connection_failer
            gSMSModelOpenState = False
        End If
    End If
    
    gSMSModelSendState = False
    
    Exit Sub
ExecuteError:
    Debug.Print "错误信息:" & Err.Description
    Dim info
    info = MsgBox("错误信息((ReadFailedMsg):" & Err.Description, vbInformation, "错误信息")
    
End Sub

'读取新短信
Private Sub ReadNewMsg()
     '屏蔽错误
    On Error GoTo ExecuteError
    
    If Not gSMSModelOpenState Then Exit Sub
    
    If gSMSModelSendState Then Exit Sub
    
    gSMSModelSendState = True
    
    Dim NewMsg As String
    NewMsg = String(255, " ")
    If GetNewMsg(CLng(gComPort), NewMsg) Then
        If NewMsg <> "" Then
            Debug.Print NewMsg
        End If
    End If
    
     gSMSModelSendState = False
     Exit Sub
ExecuteError:
    Debug.Print "错误信息:" & Err.Description
    Dim info
    info = MsgBox("错误信息((ReadNewMsg):" & Err.Description, vbInformation, "错误信息")
    
End Sub

'读取下一条要发送的短信
Private Sub ReadNextSendMsg()
     '屏蔽错误
    On Error GoTo ExecuteError
    
    If Not gSMSModelOpenState Then Exit Sub
    
     If gSMSModelSendState Then Exit Sub
    
    gSMSModelSendState = True
    
    Dim NextSendMsg As String
    NextSendMsg = String(255, " ")
    
    If GetNextSendMsg(CLng(gComPort), NextSendMsg, False) Then
       MsgBox NextSendMsg
    Else
        
    End If
    
    gSMSModelSendState = False
    
    Exit Sub
ExecuteError:
    Debug.Print "错误信息:" & Err.Description
    Dim info
    info = MsgBox("错误信息(ReadNextSendMsg):" & Err.Description, vbInformation, "错误信息")
    
End Sub

'--------------------*************数据操作**************---------

Private Sub timeGetSendData_Timer()
    
    If Not gConnectionState Then Exit Sub
    
    '屏蔽错误
    On Error GoTo ExecuteError
    
    Dim rs As ADODB.Recordset
    Dim sql As String
    
     '是否开启,设置短心中心号码
    If mdlMain.gSMSModelOpenState Then
       If GetSMSSCA = "" Then
         Call SetSMSSCA
       End If
    Else
        Exit Sub
    End If
    
    sql = "select top 1 * from smsSend where smsSendState <>'已发送' and sendTime < 6 order by id desc  "
    
    Set rs = New ADODB.Recordset
    rs.CursorType = adOpenDynamic
    rs.Open sql, gConnection
    
    If Not rs.EOF Then
        Debug.Print rs("smsNo")
        Dim sendTime As Integer
        If rs("sendTime") = "" Or rs("sendTime") = Null Then
             sendTime = 1
        Else
             sendTime = rs("sendTime") + 1
        End If
       
        If smsSend(rs("smsContext"), rs("smsNo")) Then
          sql = "update smsSend set smsSendState ='已发送' ,sendTime =" & sendTime & " where id='" & rs("id") & "'"
        Else
          sql = "update smsSend set smsSendState ='发送失败' ,sendTime =" & sendTime & " where id='" & rs("id") & "'"
        End If
        gConnection.Execute sql
    Else
        
        '读取新短信
        Call ReadNewMsg
    End If
    rs.Close
    
    Exit Sub
ExecuteError:
    Debug.Print "错误信息:" & Err.Description
    gConnectionState = False
    gSysState = mdlMain.gDB_connection_failer
    Dim info
    info = MsgBox("错误信息(SendData_Timer):" & Err.Description, vbInformation, "错误信息")
    
End Sub

'连接数据库
Private Sub timerDBconnect_Timer()
    
    If gConnectionState Then Exit Sub
    
    On Error GoTo ExecuteSQL_Error
    
    Dim connectString As String
    connectString = "Provider=SQLOLEDB.1;Persist Security Info=False;User ID=" & gUserName & ";Initial Catalog=petDB;Data Source=" & gServerURL & ";Password=" & gPassWord
    gConnection.Open connectString
    gConnectionState = True
    
    '开启设备
    Call startSMS
    
ExecuteSQL_Error:
    Debug.Print Err.Description, vbCritical, "错误信息"
    gConnectionState = False
    gSysState = mdlMain.gDB_connection_failer
    
End Sub
