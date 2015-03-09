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
   StartUpPosition =   3  '����ȱʡ
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
      Caption         =   "�����˵�"
      Visible         =   0   'False
      Begin VB.Menu mnuConfigSysInfo 
         Caption         =   "������Ϣ"
      End
      Begin VB.Menu mnuLine1 
         Caption         =   "-"
      End
      Begin VB.Menu mnuStartSmsServer 
         Caption         =   "���������豸"
      End
      Begin VB.Menu mnuCloseSmsServer 
         Caption         =   "�رն����豸"
      End
      Begin VB.Menu mnuLine2 
         Caption         =   "-"
      End
      Begin VB.Menu mnuExit 
         Caption         =   "�˳�ϵͳ"
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

Private LastState As Integer            '����ԭ����״̬

'=====dwMessage����������NIM_ADD��NIM_DELETE��NIM_MODIFY ��ʶ��֮һ======

Private Const NIM_ADD = &H0             '��������������һ��ͼ��
Private Const NIM_DELETE = &H2          'ɾ���������е�һ��ͼ��
Private Const NIM_MODIFY = &H1          '�޸��������и�ͼ����Ϣ

Private Const NIF_MESSAGE = &H1         'NOTIFYICONDATA�ṹ��uFlags�Ŀ�����Ϣ
Private Const NIF_ICON = &H2
Private Const NIF_TIP = &H4

Private Const WM_MOUSEMOVE = &H200      '��ͼ�����ƶ����
Private Const WM_LBUTTONDOWN = &H201    '����������
Private Const WM_LBUTTONUP = &H202      '�������ͷ�
Private Const WM_LBUTTONDBLCLK = &H203  '˫��������
Private Const WM_RBUTTONDOWN = &H204    '����Ҽ�����
Private Const WM_RBUTTONUP = &H205      '����Ҽ��ͷ�
Private Const WM_RBUTTONDBLCLK = &H206  '˫������Ҽ�
Private Const WM_SETHOTKEY = &H32       '��Ӧ��������ȼ�

Private Type NOTIFYICONDATA
  
    cbSize As Long                         '�����ݽṹ�Ĵ�С
    hwnd As Long                           '������������ͼ��Ĵ��ھ��
    uID As Long                            '�������������ͼ��ı�ʶ
    uFlags As Long                         '������ͼ�깦�ܿ��ƣ�����������ֵ����ϣ�һ��ȫ������
                                           'NIF_MESSAGE ��ʾ���Ϳ�����Ϣ��
                                           'NIF_ICON��ʾ��ʾ�������е�ͼ�ꣻ
                                           'NIF_TIP��ʾ�������е�ͼ���ж�̬��ʾ��
    uCallbackMessage As Long               '������ͼ��ͨ�������û����򽻻���Ϣ���������Ϣ�Ĵ�����hWnd����
    hIcon As Long                          '�������е�ͼ��Ŀ��ƾ��
    szTip As String * 64                   'ͼ�����ʾ��Ϣ
End Type

Dim myData As NOTIFYICONDATA
 

'����������
Private Sub cmdHide_Click()
    Me.Visible = False
End Sub

Private Sub Form_Load()
    
    '�ж���ʾģʽ
    If WindowState = vbMinimized Then
      LastState = vbNormal
    Else
      LastState = WindowState
    End If
    
    '���������̵�ͼ������ʾ��xֵ
    Me.ScaleMode = vbPixels
    
    '�������̲���
    With myData
      .cbSize = Len(myData)
      .hwnd = Me.hwnd
      .uID = 0
      .uFlags = NIF_ICON Or NIF_MESSAGE Or NIF_TIP
      .uCallbackMessage = WM_MOUSEMOVE
      .hIcon = Me.Icon.Handle                             'Ĭ��Ϊ����ͼ��
      .szTip = "���ŷ��ͷ�����" & vbNullChar
    End With
    
    Shell_NotifyIcon NIM_ADD, myData
    Me.Visible = False
    
End Sub


Private Sub Form_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
   
    Select Case CLng(X)
    Case WM_RBUTTONUP                                  '�����ͼ�����һ�ʱ�����˵�
    
        Me.PopupMenu popMnuTray
        
    Case WM_LBUTTONUP                                  '�����ͼ�������ʱ���� ��ʾ��Ϣ
    
          Me.Visible = False
          frmInfo.Show
         
    Case WM_LBUTTONDBLCLK                              '˫��������
                
    Case WM_MOUSEMOVE
    
         'Me.Visible = False
         'frmInfo.Show
         
    End Select
  
End Sub

'�رշ�����
Private Sub mnuCloseSmsServer_Click()
    Call closeSMS
End Sub

'���÷�����
Private Sub mnuConfigSysInfo_Click()
    frmConfig.Show
End Sub

'�˳�ϵͳ
Private Sub mnuExit_Click()
   Shell_NotifyIcon NIM_DELETE, myData
   Call ForceClose
   End
End Sub

'����������
Private Sub mnuStartSmsServer_Click()
    If Not gSMSModelOpenState Then
        Call openSMS
    End If
End Sub

'��������
Public Sub startApp()
    
     '���δ���
    On Error Resume Next
    
    '�ж���ʾģʽ
    If WindowState = vbMinimized Then
      LastState = vbNormal
    Else
      LastState = WindowState
    End If
    
    '���������̵�ͼ������ʾ��xֵ
    Me.ScaleMode = vbPixels
    
    '�������̲���
    With myData
      .cbSize = Len(myData)
      .hwnd = Me.hwnd
      .uID = 0
      .uFlags = NIF_ICON Or NIF_MESSAGE Or NIF_TIP
      .uCallbackMessage = WM_MOUSEMOVE
      .hIcon = Me.Icon.Handle                             'Ĭ��Ϊ����ͼ��
      .szTip = "���ŷ��ͷ�����" & vbNullChar
    End With
    
    Shell_NotifyIcon NIM_ADD, myData
    Me.Visible = False
    
    Call startSMS
End Sub

'�����豸
Public Sub startSMS()
     '�Ƿ�����ݿ�����
    If gConnectionState Then
       Call openSMS
    End If
   
End Sub

'----------*********** ���Ų��� **********-------
'�ر�ģ��
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

'����ģ��
Private Sub openSMS()
    
     '���δ���
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

'��ȡ�������ĺ���
Private Function GetSMSSCA() As String
    
     '���δ���
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

'���ö������ĺ���
Private Sub SetSMSSCA()
     
     '���δ���
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


'���ŷ���
Private Function smsSend(ByVal smsContext, ByVal smsNo)
     
     '���δ���
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
    Debug.Print "������Ϣ:" & Err.Description
        
End Function

'ǿ�ƹر�
Private Sub ForceClose()
   
    If Not gSMSModelOpenState Then Exit Sub
    
    ForceCloseComm (CLng(gComPort))
    
End Sub

'ǿ�ƹر�
Private Sub Form_Unload(Cancel As Integer)

    If Not gSMSModelOpenState Then Exit Sub
    ForceCloseComm (CLng(gComPort))
    
End Sub

'��ȡ����ʧ��
Private Sub ReadFailedMsg()
     '���δ���
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
    Debug.Print "������Ϣ:" & Err.Description
    Dim info
    info = MsgBox("������Ϣ((ReadFailedMsg):" & Err.Description, vbInformation, "������Ϣ")
    
End Sub

'��ȡ�¶���
Private Sub ReadNewMsg()
     '���δ���
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
    Debug.Print "������Ϣ:" & Err.Description
    Dim info
    info = MsgBox("������Ϣ((ReadNewMsg):" & Err.Description, vbInformation, "������Ϣ")
    
End Sub

'��ȡ��һ��Ҫ���͵Ķ���
Private Sub ReadNextSendMsg()
     '���δ���
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
    Debug.Print "������Ϣ:" & Err.Description
    Dim info
    info = MsgBox("������Ϣ(ReadNextSendMsg):" & Err.Description, vbInformation, "������Ϣ")
    
End Sub

'--------------------*************���ݲ���**************---------

Private Sub timeGetSendData_Timer()
    
    If Not gConnectionState Then Exit Sub
    
    '���δ���
    On Error GoTo ExecuteError
    
    Dim rs As ADODB.Recordset
    Dim sql As String
    
     '�Ƿ���,���ö������ĺ���
    If mdlMain.gSMSModelOpenState Then
       If GetSMSSCA = "" Then
         Call SetSMSSCA
       End If
    Else
        Exit Sub
    End If
    
    sql = "select top 1 * from smsSend where smsSendState <>'�ѷ���' and sendTime < 6 order by id desc  "
    
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
          sql = "update smsSend set smsSendState ='�ѷ���' ,sendTime =" & sendTime & " where id='" & rs("id") & "'"
        Else
          sql = "update smsSend set smsSendState ='����ʧ��' ,sendTime =" & sendTime & " where id='" & rs("id") & "'"
        End If
        gConnection.Execute sql
    Else
        
        '��ȡ�¶���
        Call ReadNewMsg
    End If
    rs.Close
    
    Exit Sub
ExecuteError:
    Debug.Print "������Ϣ:" & Err.Description
    gConnectionState = False
    gSysState = mdlMain.gDB_connection_failer
    Dim info
    info = MsgBox("������Ϣ(SendData_Timer):" & Err.Description, vbInformation, "������Ϣ")
    
End Sub

'�������ݿ�
Private Sub timerDBconnect_Timer()
    
    If gConnectionState Then Exit Sub
    
    On Error GoTo ExecuteSQL_Error
    
    Dim connectString As String
    connectString = "Provider=SQLOLEDB.1;Persist Security Info=False;User ID=" & gUserName & ";Initial Catalog=petDB;Data Source=" & gServerURL & ";Password=" & gPassWord
    gConnection.Open connectString
    gConnectionState = True
    
    '�����豸
    Call startSMS
    
ExecuteSQL_Error:
    Debug.Print Err.Description, vbCritical, "������Ϣ"
    gConnectionState = False
    gSysState = mdlMain.gDB_connection_failer
    
End Sub
