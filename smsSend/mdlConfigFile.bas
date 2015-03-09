Attribute VB_Name = "mdlConfigFile"
Option Explicit

Declare Function GetPrivateProfileIntA Lib "kernel32" (ByVal lpAppName As String, ByVal lpKeyName As String, ByVal nDefault As Long, ByVal lpFileName As String) As Long
Declare Function GetPrivateProfileStringA Lib "kernel32" (ByVal lpAppName As String, ByVal lpKeyName As Any, ByVal lpDefault As String, ByVal lpReturnedString As String, ByVal nSize As Long, ByVal lpFileName As String) As Long
Declare Function WritePrivateProfileStringA Lib "kernel32" (ByVal lpAppName As String, ByVal lpKeyName As Any, ByVal lpString As Any, ByVal lpFileName As String) As Long

'功能:取得配置文件中的文本值
Public Function GetAppIniText(ByVal section As String, ByVal key As String, ByVal default As String, Optional ByVal DoTrim As Boolean = True) As String
    On Error GoTo Err
    Dim text As String * 200
    Call GetPrivateProfileStringA(section, key, default, text, Len(text), gIniPath)
    GetAppIniText = Left(text, InStr(text, Chr(0)) - 1)
    If DoTrim Then GetAppIniText = Trim(GetAppIniText)
    Exit Function
Err:
    RptError Err.Description, "取本地配置参数"
    End
End Function

'功能：取得配置文件中的long值
Public Function GetAppIniLong(ByVal section As String, ByVal key As String, ByVal default As Long) As Long
    On Error GoTo Err
    GetAppIniLong = GetPrivateProfileIntA(section, key, default, gIniPath)
    Exit Function
Err:
    RptError Err.Description, "取本地配置参数"
    End
End Function

'功能：写配置文件
Public Function PutAppIniText(ByVal section As String, ByVal key As String, ByVal value As String) As String
    On Error GoTo Err
    PutAppIniText = WritePrivateProfileStringA(section, key, value, gIniPath)
    Exit Function
Err:
    RptError Err.Description, "写本地配置参数"
    PutAppIniText = False
End Function

'功能：出错信息
Public Sub RptError(MsgTxt As String, Optional Caption As String)
    Beep
    If Caption = "" Then Caption = "出错信息"
    MsgBox MsgTxt, vbCritical, "小叮当：" & Caption
End Sub
