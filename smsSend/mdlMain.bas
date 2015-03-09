Attribute VB_Name = "mdlMain"
Option Explicit
Public gIniPath As String            '配置文件的路径

Public gServerURL As String          '服务器的url地址

Public gConnectionState As Boolean     '连接数据库状态

Public gSMSModelOpenState As Boolean     '设备开启状态

Public gSMSModelSendState As Boolean     '是否正在发送

Public gCallTime As Integer          '每隔多少时间连接服务器（以秒为单位）

Public gAutoLogin As String          '是否记住密码

Public gAutoRun As String            '开机时自动运行

Public gSysState As String           '系统状态

Public gUserName As String           '全局用户名

Public gPassWord As String           '全局密码

Public gSourceDB As String           '数据源库

Public gComPort  As String           'com端口

Public gSCA  As String               '短信中心号码

Public Const gDB_connection_failer = "连接数据失败"

Public Const gSMSState_connection_failer = "设备开启失败"

Public Const gSMSState_connection_success = "设备开启成功"

Public Const gSMSState_connection_close = "设备已关闭"

Public gConnection As ADODB.Connection       '连接

Public gRs        As ADODB.Recordset       '结果集

'功能：初始化配置
Public Sub Main()
    
     '屏蔽错误
    On Error Resume Next
   
    '如果系统已运行就退出系统
    If App.PrevInstance Then End
    
    gIniPath = App.Path & "\config.ini"
    gServerURL = GetAppIniText("参数配置", "serverURL", "http://www.sina.com.cn")
    gAutoLogin = GetAppIniText("参数配置", "autologin", "false")
    gUserName = GetAppIniText("参数配置", "username", "user")
    gPassWord = StringEnDeCodecn(GetAppIniText("参数配置", "password", "password"), 80)
    gAutoRun = GetAppIniLong("参数配置", "autoRun", 0)
    gComPort = GetAppIniText("参数配置", "comPort", "1")
    gSCA = GetAppIniText("参数配置", "sca", "13800571500")
    gSourceDB = GetAppIniText("参数配置", "sourceDB", "petsDB")
    
    
    '连接数据库
    If Not ConnectServer Then
        gSysState = gDB_connection_failer
    End If
        
    Call frmMain.startApp
    
    '设置应用开机时自动启动
    Call actRunValue(1)
End Sub

'连接数据库
Public Function ConnectServer() As Boolean
    
    On Error GoTo ExecuteSQL_Error

    Dim connectString As String
    connectString = "Provider=SQLOLEDB.1;Persist Security Info=False;User ID=" & gUserName & ";Initial Catalog=" & gSourceDB & ";Data Source=" & gServerURL & ";Password=" & gPassWord
    
    Set gConnection = New ADODB.Connection
    Debug.Print connectString
    gConnection.CommandTimeout = 25
    gConnection.Open connectString
    
    ConnectServer = True
    gConnectionState = True
    
    Exit Function
ExecuteSQL_Error:
    Debug.Print Err.Description, vbCritical, "错误信息"
    ConnectServer = False
    gConnectionState = False
   
End Function


'保存配置参数
Public Sub saveConfigValue()

   Call PutAppIniText("参数配置", "serverURL", gServerURL)
   Call PutAppIniText("参数配置", "username", gUserName)
   Call PutAppIniText("参数配置", "password", StringEnDeCodecn(gPassWord, 80))
   Call PutAppIniText("参数配置", "sca", gSCA)
   Call PutAppIniText("参数配置", "comPort", gComPort)
   Call PutAppIniText("参数配置", "sourceDB", gSourceDB)
 
End Sub


'该函数只对中西文起到加密作用
'参数为：源文件，密码
Public Function StringEnDeCodecn(ByVal strSource As String, ByVal MA As Integer) As String

    On Error GoTo ErrEnDeCode
    Dim X As Single, i As Integer
    Dim CHARNUM As Long, RANDOMINTEGER As Integer
    Dim SINGLECHAR As String * 1
    Dim strTmp As String
    If MA < 0 Then
    MA = MA * (-1)
    End If
    X = Rnd(-MA)
    For i = 1 To Len(strSource) Step 1 '取单字节内容
    SINGLECHAR = Mid(strSource, i, 1)
    CHARNUM = Asc(SINGLECHAR)
g:

    RANDOMINTEGER = Int(127 * Rnd)
    If RANDOMINTEGER < 30 Or RANDOMINTEGER > 100 Then GoTo g
    CHARNUM = CHARNUM Xor RANDOMINTEGER
    strTmp = strTmp & Chr(CHARNUM)
    Next i
    StringEnDeCodecn = strTmp
    Exit Function
ErrEnDeCode:
    StringEnDeCodecn = ""
    MsgBox "错误信息(StringEnDeCodecn):" & Err.Number & "\" & Err.Description
End Function


