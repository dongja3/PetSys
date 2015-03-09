VERSION 5.00
Begin VB.Form frmConfig 
   Caption         =   "系统配置"
   ClientHeight    =   4635
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   4515
   Icon            =   "frmConfig.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   ScaleHeight     =   4635
   ScaleWidth      =   4515
   StartUpPosition =   2  '屏幕中心
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
      Caption         =   "退出"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "保存"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "目标数据库"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "COM端口"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "短信中心号码"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "密 码"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "用户名"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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
      Caption         =   "服务器的url"
      BeginProperty Font 
         Name            =   "楷体_GB2312"
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

'保存配置信息
Private Sub cmdSave_Click()

   Dim serverUrl As String
   Dim userName As String
   Dim password As String
   
   If Trim(Me.txtServerUrl) = "" Then
     msgReturn = MsgBox("服务器url不能为空", vbInformation, "系统提示信息")
     txtServerUrl.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtUserName) = "" Then
     msgReturn = MsgBox("服务器登陆用户名不能为空", vbInformation, "系统提示信息")
     txtUserName.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtSourceDB.text) = "" Then
     msgReturn = MsgBox("目标数据库不能为空", vbInformation, "系统提示信息")
     txtSourceDB.SetFocus
     Exit Sub
   End If
   
   If Trim(Me.txtPassword.text) = "" Then
     msgReturn = MsgBox("服务器登陆密码不能为空", vbInformation, "系统提示信息")
     txtPassword.SetFocus
     Exit Sub
   End If
   
   
   gServerURL = Trim(Me.txtServerUrl)
   gUserName = Trim(Me.txtUserName)
   gPassWord = Trim(Me.txtPassword)
   mdlMain.gComPort = Trim(Me.cblComPort.text)
   mdlMain.gSCA = Trim(Me.txtSCA.text)
   mdlMain.gSourceDB = Trim(Me.txtSourceDB.text)
   
   
   '保存配置值
   Call saveConfigValue
   MsgBox "只有再启动服务器时,参数才生效", vbInformation, "提示信息"
   Unload Me
End Sub

'退出系统
Private Sub Command2_Click()
    Unload Me
End Sub

