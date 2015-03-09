Attribute VB_Name = "mdlReg"
Option Explicit
Declare Function RegOpenKeyEx Lib "advapi32.dll" Alias "RegOpenKeyExA" (ByVal hkey As Long, ByVal lpSubKey As String, ByVal ulOptions As Long, ByVal samDesired As Long, phkResult As Long) As Long
Declare Function RegCloseKey Lib "advapi32.dll" (ByVal hkey As Long) As Long
Declare Function RegSetValueExString Lib "advapi32.dll" Alias "RegSetValueExA" (ByVal hkey As Long, ByVal lpValueName As String, ByVal Reserved As Long, ByVal dwType As Long, ByVal lpValue As String, ByVal cbData As Long) As Long
Declare Function RegDeleteValue Lib "advapi32.dll" Alias "RegDeleteValueA" (ByVal hkey As Long, ByVal lpValueName As String) As Long
Enum RootKey
    HKEY_CLASSES_ROOT = &H80000000
    HKEY_CURRENT_USER = &H80000001
    HKEY_LOCAL_MACHINE = &H80000002
    HKEY_USERS = &H80000003
    HKEY_PERFORMANCE_DATA = &H80000004
    HKEY_CURRENT_CONFIG = &H80000005
    HKEY_DYN_DATA = &H80000006
End Enum
Enum ErrorCode
    ERROR_SUCCESS = 0&
    ERROR_MORE_DATA = 234&
End Enum
Const KEY_ALL_ACCESS = &H3F
Enum ValueType
    REG_NONE = 0
    REG_SZ = 1
    REG_EXPAND_SZ = 2
    REG_BINARY = 3
    REG_DWORD = 4
    REG_DWORD_BIG_ENDIAN = 5
    REG_MULTI_SZ = 7
End Enum

Function SetRunValue(Rname As String, Rvalue As String)
    Dim Retval As Long
    Dim skeyname As String
    Dim hkey As Long
    skeyname = "Software\Microsoft\Windows\CurrentVersion\Run"
    Retval = RegOpenKeyEx(HKEY_LOCAL_MACHINE, skeyname, 0, KEY_ALL_ACCESS, hkey)
    If Retval = 0 Then
        Rvalue = Rvalue & Chr$(0)
        Retval = RegSetValueExString(hkey, Rname, 0&, REG_SZ, Rvalue, Len(Rvalue))
    End If
    RegCloseKey (hkey)
End Function

Function DelRunValue(Rname As String)
    Dim hkey As Long
    Dim Retval As Long
    Dim skeyname As String
    skeyname = "Software\Microsoft\Windows\CurrentVersion\Run"
    Retval = RegOpenKeyEx(HKEY_LOCAL_MACHINE, skeyname, 0, KEY_ALL_ACCESS, hkey)
    Retval = RegDeleteValue(hkey, Rname)
    RegCloseKey (hkey)
End Function


'函数：autoRun
'功能:设置、删除注册表键值
Function actRunValue(ByVal actVal As Integer) As Boolean
    Dim actValue As Integer
    actValue = actVal
    Select Case actValue
    Case 1
        '设置键值
        SetRunValue App.EXEName, App.Path & "\" & App.EXEName & ".exe"
    Case 2
        '删除键值
         DelRunValue App.EXEName
    Case Else
    
    End Select
End Function

