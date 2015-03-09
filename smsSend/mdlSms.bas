Attribute VB_Name = "mdlSms"

Public Declare Function OpenComm Lib "AscendSMS.dll" (ByVal CommIndex As Long) As Long
Public Declare Function CloseComm Lib "AscendSMS.dll" (ByVal CommIndex As Long) As Long
Public Declare Function SendMsg Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal Msg As String, ByVal MobileNo As String, ByVal MsgIndex As Long, ByVal Chinese As Boolean) As Long
Public Declare Function GetNewMsg Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal NewMsg As String) As Boolean
Public Declare Function GetUnSendCount Lib "AscendSMS.dll" (ByVal CommIndex As Long) As Long
Public Declare Function GetSCA Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal SCA As String) As Boolean
Public Declare Function SetSCA Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal SCA As String) As Boolean
Public Declare Function GetFailedMsg Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal Msg As String) As Boolean
Public Declare Function GetNextSendMsg Lib "AscendSMS.dll" (ByVal CommIndex As Long, ByVal Msg As String, ByVal DeleteAfterRead As Boolean) As Boolean
Public Declare Function ForceCloseComm Lib "AscendSMS.dll" (ByVal CommIndex As Long) As Long

