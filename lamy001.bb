; Include "other stuff.bb"


; SETUP

Graphics 640,480
AppTitle "The Legend of Lamy"
SetBuffer BackBuffer()

Global StoryState = 0, Inventory = 0

inty% = 3
stringy$ = "You wake up drenched in sweat in your bed"


Type NPC
	Field name$
	Field image
	Field position
	Field states ; first meeting is state 0, etc...
End Type

;Global mine.MyType Dim all_mine.MyType( 100 ) 
Dim NPCs.NPC(100)

NPCs(0) = New NPC
NPCs(0)\name$ = "John"


; Start

; Name$ = Input$("Geben Sie Ihren Namen ein: ")

.label


; GRAFIKBEFEHLE

; DrawBlock, TileBlock, CopyImage: Diese befehle arbeiten etwas schneller als Image-Befehle, weil sie den ursprünglichen Hintergrund nicht erhalten müssen
; SaveImage, SaveBuffer
; ViewPort, Origin
; Text = Grafikmodus anstatt Print
; CreateBank Speicherbaenke fuer andere grosse dateien, um sie zuerst in den Arbeitsspeicher zu laden und dann erst schritt fuer schritt auszulesen, e.g. leveldateien, ...
; optimize collision: only check if bounding boxes overlap, only if they do repeat check more granularly
; Flip(False) ; awesome hack from some dude online, turns off 60fps cap

Global x=300 
Global y=300 
Global playerBoxWidth = 30
Global playerBoxHeight = 15 
;Global playerTurned = 0 ; how many degrees the player is turned, starting with 0 being "12 oclock"
playerMovementX = 0 playerMovementY = 0 speed = 3

map = LoadImage("map001.bmp")
MaskImage map, 255, 255, 255 ; make white transparent instead of black
Color 0,0,0
ClsColor 255, 255, 255

Repeat

	Cls
	DrawImage map, 0, 0
	DrawPlayer()
	Flip()
	
	playerMovementX = 0 playerMovementY = 0

	
	If KeyDown(200) = 1 Then playerMovementY = -speed
	If KeyDown(208) = 1 Then playerMovementY = speed
	If KeyDown(205) = 1 Then playerMovementX = speed
	If KeyDown(203) = 1 Then playerMovementX = -speed
	
	x = x + playerMovementX
	y = y + playerMovementY 
	
	; If collide, move player back to pre-collision
	If ImageRectCollide (map,0,0,0,x,y,playerBoxWidth ,playerBoxHeight ) Then
		x = x - playerMovementX
		y = y - playerMovementY 
	EndIf	
	

Until KeyDown(1) = 1






; crates endless loop Goto label


WaitKey
End


; possible subroutines come here, after End



Function DrawPlayer()
	Oval x, y, playerBoxWidth ,playerBoxHeight, 0
	Oval x + 7, y, playerBoxWidth - 14 ,playerBoxHeight, 0	
End Function

Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful, especially in other files so I can just import instead of readfile etc.