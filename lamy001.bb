Include "maps.bb"


; SETUP

Graphics 640,480
AppTitle "The Legend of Lamy"
SetBuffer BackBuffer()

Global StoryState = 0, Inventory = 0

inty% = 3
stringy$ = "You wake up drenched in sweat in your bed"



; ok so the problems with custom types is that they all live in the same list
; so if I want to be able to seperate them without having to go through globally ALL instances of the same type
; I will need to rather use Dims or so
Type NPC
	Field name$
	Field image
	Field position
	Field states ; first meeting is state 0, etc...
End Type


Type EventArea
	Field name$
	Field x
	Field y
	Field width
	Field height
	Field e.Event
	Field enabled ; first meeting is state 0, etc...
End Type

Type Event
	Field name$
	Field ea.EventArea
	Field enabled ; first meeting is state 0, etc...
End Type
 
Dim NPCs.NPC(100) ;the dot means something entirely different here than in other languages
					; basically the dot means "of Type ..."
NPCs(0) = New NPC
NPCs(0)\name$ = "John"

Dim EventAreas.EventArea(100)
EventAreas(0) = New EventArea
EventAreas(0)\name$ = "Talk to the barkeeper"
EventAreas(0)\x = "Talk to the barkeeper"
EventAreas(0)\y = "Talk to the barkeeper"
EventAreas(0)\width = "Talk to the barkeeper"



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

; TODO make coordinates relative, so the game would technically be scalable

; apparently it's not possible to make Global Constants, so here we go
Const RECHTS = 1
Const UNTEN = 2
Const LINKS = 3

Global x=300 
Global y=300 
Global playerBoxX Global playerBoxY
Global playerBoxWidth = 30
Global playerBoxHeight = 30
;Global playerTurned = 0 ; how many degrees the player is turned, starting with 0 being "12 oclock"
Global playerMovementX = 0 Global playerMovementY = 0
Global speed = 3

Global map = LoadImage("map001.bmp")
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
	
	; TODO maybe using TFormFilter mixed with a buffer that we draw to every time and rotate from "base image"
		; could enable a nice GTA2 like movement
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
	; TODO draw the maps out fully and then start deleting to create "sub" maps.
		; collision map >> delete "walkable" objects like grass, stools, walkways, ...
		; event map >> delete everything that can't interact (leaving maybe only characters)
		; basically create layers by deleting from the original "graphic"
		; OR
		; use something fancy like Gimp to layer them and then export all layers into individual files
	If ImageRectCollide (map,0,0,0,playerBoxX,playerBoxY,playerBoxWidth ,playerBoxHeight ) Then
		UndoMovePlayer()
	EndIf	
	
	CheckEvents()

Until KeyDown(1) = 1






; crates endless loop Goto label


WaitKey
End


; possible subroutines come here, after End


Function CheckEvents()

; ok NOW we want to be able to talk to people
; options
; simple event system like RPG maker. If you stand in area x, then you can do y
	; options
	; draw events in other colors in paint
		; but then how to "remove" those colors again?
			; do now, worry later?
			; problem is, I can't detect overlap of different colors. 
				; so it would make more sense to have layers because I can detect it then if it's a seperate image
	; just write coordinates in code and have a "debug mode" where it shows those areas in different colors
		; yeah I like that better
		; that would probably be a custom type "eventArea"
			; ALTHOUGH then I'll need to use rectangular areas or so
	If x > 640 Then
		Stop
		LoadMap(RECHTS)
		x = 20
	Else If x < 0 Then
		LoadMap(LINKS)
		x = 620
	Else If y > 480 Then
		LoadMap(UNTEN)	
		y = 20			
	EndIf
End Function

; TODO make it actually into a map system, but for now we don't need it that badly
Function LoadMap(direction)
	If direction = RECHTS Then
		map = LoadImage("map002.bmp"); of course this is hardcoded, so needs to be replaced with a more dynamic map system
		MaskImage map, 255, 255, 255 ; make white transparent instead of black
	EndIf
	If direction = UNTEN Then
		map = LoadImage("map003.bmp"); of course this is hardcoded, so needs to be replaced with a more dynamic map system
		MaskImage map, 255, 255, 255 ; make white transparent instead of black
	EndIf	
	If direction = LINKS Then
		map = LoadImage("map004.bmp"); of course this is hardcoded, so needs to be replaced with a more dynamic map system
		MaskImage map, 255, 255, 255 ; make white transparent instead of black
	EndIf		
End Function

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