VERSION 5.00
Begin VB.Form frmConfig 
   Caption         =   "ϵͳ����"
   ClientHeight    =   4635
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   4515
   Icon            =   "frmConfig.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   4635
   ScaleWidth      =   4515
   StartUpPosition =   2  '��Ļ����
   Begin VB.TextBox txtSourceDB 
      Height          =   390
      Left            =   1800
      TabIndex        =   13
      Top             =   960
      Width           =   2175
   End
   Begin VB.ComboBox cblComPort 
      Height          =   300
      ItemData        =   "frmConfig.frx":000C
      Left            =   1800
      List            =   "frmConfig.frx":0019
      TabIndex        =   11
      Text            =   "1"
      Top             =   3360
      Width           =   2055
   End
   Begin VB.TextBox txtSCA 
      Height          =   375
      Left            =   1800
      TabIndex        =   9
      Top             =   2880
      Width           =   2175
   End
   Begin VB.CommandButton Command2 
      Caption         =   "�˳�"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   3000
      TabIndex        =   7
      Top             =   4080
      Width           =   1095
   End
   Begin VB.CommandButton cmdSave 
      Caption         =   "����"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   375
      Left            =   1320
      TabIndex        =   6
      Top             =   4080
      Width           =   1215
   End
   Begin VB.TextBox txtPassword 
      Height          =   375
      IMEMode         =   3  'DISABLE
      Left            =   1800
      PasswordChar    =   "*"
      TabIndex        =   5
      Top             =   2280
      Width           =   2175
   End
   Begin VB.TextBox txtUserName 
      Height          =   375
      Left            =   1800
      TabIndex        =   2
      Top             =   1620
      Width           =   2175
   End
   Begin VB.TextBox txtServerUrl 
      Height          =   375
      Left            =   1800
      TabIndex        =   1
      Top             =   360
      Width           =   2175
   End
   Begin VB.Label Label6 
      AutoSize        =   -1  'True
      Caption         =   "Ŀ�����ݿ�"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   12
      Top             =   960
      Width           =   900
   End
   Begin VB.Label Label5 
      AutoSize        =   -1  'True
      Caption         =   "COM�˿�"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   10
      Top             =   3360
      Width           =   630
   End
   Begin VB.Label Label4 
      AutoSize        =   -1  'True
      Caption         =   "�������ĺ���"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   8
      Top             =   2880
      Width           =   1080
   End
   Begin VB.Label Label3 
      AutoSize        =   -1  'True
      Caption         =   "�� ��"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   4
      Top             =   2280
      Width           =   450
   End
   Begin VB.Label Label2 
      AutoSize        =   -1  'True
      Caption         =   "�û���"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   3
      Top             =   1620
      Width           =   540
   End
   Begin VB.Label Label1 
      AutoSize        =   -1  'True
      Caption         =   "��������url"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   180
      Left            =   480
      TabIndex        =   0
      Top             =   360
      Width           =   990
   End
End
Attribute VB_Name = "frmConfig"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False


Private Sub Form_Load()

   Me.txtServerUrl = gServerURL
   Me.txtPassword = gPassWord
   Me.txtUserName = gUserName
   Me.txtSCA.text = gSCA
   Me.cblComPort.text = gComPort
   Me.txtSourceDB.text = mdlMain.gSourceDB
   
End Sub

'����������Ϣ
Private Sub cmdSave_Click()

   Dim serverUrl As String
   Dim userName As String
   Dim password As String
   
   If Trim(Me.txtServerUrl) = "" Then
     msgReturn = MsgBox("������url����Ϊ��", vbInformation, "ϵͳ��ʾ��Ϣ")
     txtServerUrl.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtUserName) = "" Then
     msgReturn = MsgBox("��������½�û�������Ϊ��", vbInformation, "ϵͳ��ʾ��Ϣ")
     txtUserName.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtSourceDB.text) = "" Then
     msgReturn = MsgBox("Ŀ�����ݿⲻ��Ϊ��", vbInformation, "ϵͳ��ʾ��Ϣ")
     txtSourceDB.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtPassword.text) = "" Then
     msgReturn = MsgBox("��������½���벻��Ϊ��", vbInformation, "ϵͳ��ʾ��Ϣ")
     txtPassword.SetFocus
     Exit Sub
   End If
   
   
   gServerURL = Trim(Me.txtServerUrl)
   gUserName = Trim(Me.txtUserName)
   gPassWord = Trim(Me.txtPassword)
   mdlMain.gComPort = Trim(Me.cblComPort.text)
   mdlMain.gSCA = Trim(Me.txtSCA.text)
   mdlMain.gSourceDB = Trim(Me.txtSourceDB.text)
   
   
   '��������ֵ
   Call saveConfigValue
   MsgBox "ֻ��������������ʱ,��������Ч", vbInformation, "��ʾ��Ϣ"
   Unload Me
End Sub

'�˳�ϵͳ
Private Sub Command2_Click()
    Unload Me
End Sub

