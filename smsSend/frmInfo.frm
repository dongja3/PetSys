VERSION 5.00
Begin VB.Form frmInfo 
   Caption         =   "���ŷ���ϵͳ��Ϣ"
   ClientHeight    =   2265
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   2955
   Icon            =   "frmInfo.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   151
   ScaleMode       =   3  'Pixel
   ScaleWidth      =   197
   StartUpPosition =   3  '����ȱʡ
   Begin VB.Label lblSysState 
      AutoSize        =   -1  'True
      Caption         =   "������"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H000000FF&
      Height          =   180
      Left            =   1680
      TabIndex        =   5
      Top             =   1440
      Width           =   540
   End
   Begin VB.Label lblSmsSendSum 
      AutoSize        =   -1  'True
      Caption         =   "0��"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H000000FF&
      Height          =   180
      Left            =   1680
      TabIndex        =   4
      Top             =   960
      Width           =   270
   End
   Begin VB.Label lblSmsNotSend 
      AutoSize        =   -1  'True
      Caption         =   "0��"
      BeginProperty Font 
         Name            =   "����_GB2312"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H000000FF&
      Height          =   180
      Left            =   1680
      TabIndex        =   3
      Top             =   480
      Width           =   270
   End
   Begin VB.Label Label3 
      AutoSize        =   -1  'True
      Caption         =   "����������״̬"
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
      Left            =   240
      TabIndex        =   2
      Top             =   1440
      Width           =   1260
   End
   Begin VB.Label Label2 
      AutoSize        =   -1  'True
      Caption         =   "�ѷ��Ͷ�����"
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
      Left            =   240
      TabIndex        =   1
      Top             =   960
      Width           =   1080
   End
   Begin VB.Label Label1 
      AutoSize        =   -1  'True
      Caption         =   "δ��������"
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
      Left            =   240
      TabIndex        =   0
      Top             =   480
      Width           =   900
   End
End
Attribute VB_Name = "frmInfo"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Form_Load()

     '���δ���
    On Error GoTo ExecuteError
    '�ƶ����ڵ���Ļ�����·�
    Me.Move Screen.Width - 3075, Screen.Height - 3060, 3075, 2610
    lblSysState.Caption = gSysState
    
    Dim rs As ADODB.Recordset
    Dim sql As String
    
    If Not gConnectionState Then Exit Sub
    
    sql = "select count(*) from smsSend where smsSendState ='�ѷ���'"
    Set rs = New ADODB.Recordset
    rs.CursorType = adOpenDynamic
    rs.Open sql, gConnection
    If Not rs.EOF Then
        Me.lblSmsSendSum.Caption = rs(0) & "��"
    End If
    rs.Close
    
    sql = "select count(*) from smsSend where smsSendState <>'�ѷ���'"
    rs.Open sql, gConnection
    
     If Not rs.EOF Then
        Me.lblSmsNotSend.Caption = rs(0) & "��"
    End If
    rs.Close
   
    Exit Sub
    
ExecuteError:
    Debug.Print "������Ϣ:" & Err.Description
    gConnectionState = False
    Dim info
    info = MsgBox("������Ϣ:" & Err.Description, vbInformation, "������Ϣ")
    gSysState = mdlMain.gDB_connection_failer
    
End Sub

Private Sub Form_MouseMove(Button As Integer, Shift As Integer, X As Single, Y As Single)
   Unload Me
End Sub
