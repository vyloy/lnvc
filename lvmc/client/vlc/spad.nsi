;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; "set program as default" script for vlc                                        ;
; more infos at http://msdn.microsoft.com/en-us/library/bb776851%28VS.85%29.aspx ;
; and http://msdn.microsoft.com/en-us/library/bb776851%28VS.85%29.aspx           ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

!include LogicLib.nsh

Function GetOptions
	!define GetOptions `!insertmacro GetOptionsCall`
 
	!macro GetOptionsCall _PARAMETERS _OPTION _RESULT
		Push `${_PARAMETERS}`
		Push `${_OPTION}`
		Call GetOptions
		Pop ${_RESULT}
	!macroend
 
	Exch $1
	Exch
	Exch $0
	Exch
	Push $2
	Push $3
	Push $4
	Push $5
	Push $6
	Push $7
	ClearErrors
 
	StrCpy $2 $1 '' 1
	StrCpy $1 $1 1
	StrLen $3 $2
	StrCpy $7 0
 
	begin:
	StrCpy $4 -1
	StrCpy $6 ''
 
	quote:
	IntOp $4 $4 + 1
	StrCpy $5 $0 1 $4
	StrCmp $5$7 '0' notfound
	StrCmp $5 '' trimright
	StrCmp $5 '"' 0 +7
	StrCmp $6 '' 0 +3
	StrCpy $6 '"'
	goto quote
	StrCmp $6 '"' 0 +3
	StrCpy $6 ''
	goto quote
	StrCmp $5 `'` 0 +7
	StrCmp $6 `` 0 +3
	StrCpy $6 `'`
	goto quote
	StrCmp $6 `'` 0 +3
	StrCpy $6 ``
	goto quote
	StrCmp $5 '`' 0 +7
	StrCmp $6 '' 0 +3
	StrCpy $6 '`'
	goto quote
	StrCmp $6 '`' 0 +3
	StrCpy $6 ''
	goto quote
	StrCmp $6 '"' quote
	StrCmp $6 `'` quote
	StrCmp $6 '`' quote
	StrCmp $5 $1 0 quote
	StrCmp $7 0 trimleft trimright
 
	trimleft:
	IntOp $4 $4 + 1
	StrCpy $5 $0 $3 $4
	StrCmp $5 '' notfound
	StrCmp $5 $2 0 quote
	IntOp $4 $4 + $3
	StrCpy $0 $0 '' $4
	StrCpy $4 $0 1
	StrCmp $4 ' ' 0 +3
	StrCpy $0 $0 '' 1
	goto -3
	StrCpy $7 1
	goto begin
 
	trimright:
	StrCpy $0 $0 $4
	StrCpy $4 $0 1 -1
	StrCmp $4 ' ' 0 +3
	StrCpy $0 $0 -1
	goto -3
	StrCpy $3 $0 1
	StrCpy $4 $0 1 -1
	StrCmp $3 $4 0 end
	StrCmp $3 '"' +3
	StrCmp $3 `'` +2
	StrCmp $3 '`' 0 end
	StrCpy $0 $0 -1 1
	goto end
 
	notfound:
	SetErrors
	StrCpy $0 ''
 
	end:
	Pop $7
	Pop $6
	Pop $5
	Pop $4
	Pop $3
	Pop $2
	Pop $1
	Exch $0
FunctionEnd

; Remove shortcuts
!macro HideIcons
  WriteRegDWORD HKLM "Software\Clients\Media\VLC\InstallInfo" "IconsVisible" 0
  SetShellVarContext all  ; Set $DESKTOP to All Users
  ${Unless} ${FileExists} "$DESKTOP\VLC media player.lnk"
    SetShellVarContext current  ; Set $DESKTOP to the current user's desktop
  ${EndUnless}
  ${If} ${FileExists} "$DESKTOP\VLC media player.lnk"
    Delete "$DESKTOP\VLC media player.lnk"
  ${EndIf}
  ${If} ${FileExists} "$QUICKLAUNCH\VLC media player.lnk"
    Delete "$QUICKLAUNCH\VLC media player.lnk"
  ${EndIf}
!macroend
!define HideIcons "!insertmacro HideIcons"

; Adds shortcuts 
!macro ShowIcons
  WriteRegDWORD HKLM "Software\Clients\Media\VLC\InstallInfo" "IconsVisible" 1
  SetShellVarContext all  ; Set $DESKTOP to All Users
  ${Unless} ${FileExists} "$DESKTOP\VLC media player.lnk"
    CreateShortCut "$DESKTOP\VLC media player.lnk" "$EXEDIR\vlc.exe" "" "$EXEDIR\vlc.exe" 0
  ${EndUnless}
  ${Unless} ${FileExists} "$QUICKLAUNCH\VLC media player.lnk"
    CreateShortCut "$QUICKLAUNCH\VLC media player.lnk" "$EXEDIR\vlc.exe" "" "$EXEDIR\vlc.exe" 0
  ${EndUnless}
!macroend
!define ShowIcons "!insertmacro ShowIcons"

!macro Reinstall
    MessageBox MB_OK "Not implemented"
!macroend
!define Reinstall "!insertmacro reinstall"

Section
  SetShellVarContext all
  SetOutPath "$EXEDIR"

    ${GetOptions} $CMDLINE "HideIcons"  $R0
	IfErrors 0 +2
	goto showIc
    !insertmacro HideIcons
    
    showIc:
    ${GetOptions} $CMDLINE "ShowIcons"  $R0
    IfErrors 0 +2
    goto reinst
    !insertmacro ShowIcons
    
    reinst:
    ${GetOptions} $CMDLINE "Reinstall"  $R0
	IfErrors 0 +2
	goto end
    !insertmacro Reinstall
    
    end:
SectionEnd

;"setup" is aded to the file name to trigger the UAC heuristic and request admin privileges
OutFile "spad-setup.exe"
