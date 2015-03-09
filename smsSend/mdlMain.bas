Attribute VB_Name = "mdlMain"
Option Explicit
Public gIniPath As String            '�����ļ���·��

Public gServerURL As String          '��������url��ַ

Public gConnectionState As Boolean     '�������ݿ�״̬

Public gSMSModelOpenState As Boolean     '�豸����״̬

Public gSMSModelSendState As Boolean     '�Ƿ����ڷ���

Public gCallTime As Integer          'ÿ������ʱ�����ӷ�����������Ϊ��λ��

Public gAutoLogin As String          '�Ƿ��ס����

Public gAutoRun As String            '����ʱ�Զ�����

Public gSysState As String           'ϵͳ״̬

Public gUserName As String           'ȫ���û���

Public gPassWord As String           'ȫ������

Public gSourceDB As String           '����Դ��

Public gComPort  As String           'com�˿�

Public gSCA  As String               '�������ĺ���

Public Const gDB_connection_failer = "��������ʧ��"

Public Const gSMSState_connection_failer = "�豸����ʧ��"

Public Const gSMSState_connection_success = "�豸�����ɹ�"

Public Const gSMSState_connection_close = "�豸�ѹر�"

Public gConnection As ADODB.Connection       '����

Public gRs        As ADODB.Recordset       '�����

'���ܣ���ʼ������
Public Sub Main()
    
     '���δ���
    On Error Resume Next
   
    '���ϵͳ�����о��˳�ϵͳ
    If App.PrevInstance Then End
    
    gIniPath = App.Path & "\config.ini"
    gServerURL = GetAppIniText("��������", "serverURL", "http://www.sina.com.cn")
    gAutoLogin = GetAppIniText("��������", "autologin", "false")
    gUserName = GetAppIniText("��������", "username", "user")
    gPassWord = StringEnDeCodecn(GetAppIniText("��������", "password", "password"), 80)
    gAutoRun = GetAppIniLong("��������", "autoRun", 0)
    gComPort = GetAppIniText("��������", "comPort", "1")
    gSCA = GetAppIniText("��������", "sca", "13800571500")
    gSourceDB = GetAppIniText("��������", "sourceDB", "petsDB")
    
    
    '�������ݿ�
    If Not ConnectServer Then
        gSysState = gDB_connection_failer
    End If
        
    Call frmMain.startApp
    
    '����Ӧ�ÿ���ʱ�Զ�����
    Call actRunValue(1)
End Sub

'�������ݿ�
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
    Debug.Print Err.Description, vbCritical, "������Ϣ"
    ConnectServer = False
    gConnectionState = False
   
End Function


'�������ò���
Public Sub saveConfigValue()

   Call PutAppIniText("��������", "serverURL", gServerURL)
   Call PutAppIniText("��������", "username", gUserName)
   Call PutAppIniText("��������", "password", StringEnDeCodecn(gPassWord, 80))
   Call PutAppIniText("��������", "sca", gSCA)
   Call PutAppIniText("��������", "comPort", gComPort)
   Call PutAppIniText("��������", "sourceDB", gSourceDB)
 
End Sub


'�ú���ֻ���������𵽼�������
'����Ϊ��Դ�ļ�������
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
    For i = 1 To Len(strSource) Step 1 'ȡ���ֽ�����
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
    MsgBox "������Ϣ(StringEnDeCodecn):" & Err.Number & "\" & Err.Description
End Function


