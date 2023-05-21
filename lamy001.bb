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
Global playerTurned = -90 ; how many degrees the player is turned, starting with 0 being "12 oclock"
playerMovementX = 0 playerMovementY = 0 speed = 3
Global playerTurnDeg = 0

Global map = LoadImage("map001.bmp")
MaskImage map, 255, 255, 255 ; make white transparent instead of black

Global playerImage = LoadImage("player.bmp")
MaskImage playerImage, 255, 255, 255 ; make white transparent instead of black


Color 0,0,0
ClsColor 255, 255, 255

Repeat

	Cls
	DrawImage map, 0, 0
	DrawPlayer()
	Flip()
	
	
	
	playerMovementX = 0 playerMovementY = 0 playerTurnDeg = 0
	move = False
	speed = 3
	
	If KeyDown(200) = 1 Then move = True
	If KeyDown(208) = 1 Then 
		speed = -1 * speed
		move = True
	EndIf
	If KeyDown(205) = 1 Then playerTurnDeg = +2; degrees go ccw so if I turn right (CW), I subtract 1
	If KeyDown(203) = 1 Then playerTurnDeg = -2
	
	playerTurned = playerTurned + playerTurnDeg
	playerTurned = playerTurned Mod 360


	; determine direction vector and  x and y components of said vector
	xv# = speed * Cos( playerTurned ) ; (x,y) is a point on the circle 
	yv# = speed * Sin( playerTurned  ) ; corresponding to angle a. 		

	If move Then
		x = x + xv#
		y = y + yv# 
		
		; If collide, move player back to pre-collision
		If ImageRectCollide (map,0,0,0,x,y,playerBoxWidth ,playerBoxHeight ) Then
			x = x - xv#
			y = y - yv#
		EndIf	
			
		
	EndIf
	


Until KeyDown(1) = 1






; crates endless loop Goto label


WaitKey
End


; possible subroutines come here, after End



Function DrawPlayer()
	
	DrawImage playerImage, x, y
	
	; so fine-rotating the image isn't working and we will probably need rotation for other things too. Options:
	; 1) if you look at most olden games they don't have rotation unless 90deg. Although GTA2 does.
	; 2) use the 3D engine with sprites >> but we can't use pixel perfect overlap on sprites.
	; 3) use BlitzMax with a possibly better SetRotation command >> I hate the blitzmax-ng syntax. Looks a lot like C++
	; chose optione 1)
	
End Function

Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful, especially in other files so I can just import instead of readfile etc.