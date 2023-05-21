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

; DrawBlock, TileBlock, CopyImage: Diese befehle arbeiten etwas schneller als Image-Befehle, weil sie den urspr�nglichen Hintergrund nicht erhalten m�ssen
; SaveImage, SaveBuffer
; ViewPort, Origin
; Text = Grafikmodus anstatt Print
; CreateBank Speicherbaenke fuer andere grosse dateien, um sie zuerst in den Arbeitsspeicher zu laden und dann erst schritt fuer schritt auszulesen, e.g. leveldateien, ...
; optimize collision: only check if bounding boxes overlap, only if they do repeat check more granularly
; Flip(False) ; awesome hack from some dude online, turns off 60fps cap

Global x=300 
Global y=300 
Global playerBoxX Global playerBoxY
Global playerBoxWidth = 30
Global playerBoxHeight = 30
;Global playerTurned = 0 ; how many degrees the player is turned, starting with 0 being "12 oclock"
Global playerMovementX = 0 Global playerMovementY = 0
Global speed = 3

map = LoadImage("map001.bmp")
MaskImage map, 255, 255, 255 ; make white transparent instead of black

Global playerImage = LoadImage("player.bmp")
MaskImage playerImage, 255, 255, 255 ; make white transparent instead of black
Global prevPlayerRotation Global currPlayerRotation
HandleImage playerImage, 15, 7 ; set handle to center cause of rotation n stuff

Color 0,0,0
ClsColor 255, 255, 255

Repeat

	Cls
	DrawImage map, 0, 0
	DrawPlayer()
	; FOR DEBUGGING: draw collision box: Rect playerBoxX, playerBoxY, playerBoxWidth, playerBoxHeight, 0
	Flip()
	
	playerMovementX = 0 playerMovementY = 0
	prevPlayerRotation = currPlayerRotation
	
	If KeyDown(200) = 1 Then ; UP
		playerMovementY = -speed
		currPlayerRotation = 0
	EndIf
	If KeyDown(208) = 1 Then ; DOWN
		playerMovementY = speed
		currPlayerRotation = 180
	EndIf
	If KeyDown(205) = 1 Then ; RIGHT
		playerMovementX = speed
		currPlayerRotation = 90
	EndIf
	If KeyDown(203) = 1 Then ; LEFT
		playerMovementX = -speed
		currPlayerRotation = -90
	EndIf
	
	MovePlayer()
	
	
	; If collide or out of bounds, move player back to pre-collision
	; Or - and hear me out - we could just draw a frame and leave holes in it and whenever the player leaves
		; bounds we know he's entering another map
	If ImageRectCollide (map,0,0,0,playerBoxX,playerBoxY,playerBoxWidth ,playerBoxHeight ) Then
		UndoMovePlayer()
	EndIf	

Until KeyDown(1) = 1






; crates endless loop Goto label


WaitKey
End


; possible subroutines come here, after End


Function DrawPlayer()
	RotatePlayerImage()
	DrawImage playerImage, x, y
End Function

Function MovePlayer()
	x = x + playerMovementX
	y = y + playerMovementY 
	UpdatePlayerBox()
End Function

Function UndoMovePlayer()
	x = x - playerMovementX
	y = y - playerMovementY 
	UpdatePlayerBox()
End Function

Function RotatePlayerImage()
	RotateImage playerImage, prevPlayerRotation - currPlayerRotation
End Function

Function UpdatePlayerBox()
	If currPlayerRotation = 90 Or currPlayerRotation = -90 Then
		offsetX = -15 ; fuck it, let's just have a square bounding box
		offsetY = -15
	Else
		offsetX = -15
		offsetY = -15
	EndIf 

	playerBoxX = x + offsetX
	playerBoxY = y + offsetY
End Function


Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful, especially in other files so I can just import instead of readfile etc.