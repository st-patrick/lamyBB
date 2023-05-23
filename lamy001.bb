; SETUP
Graphics 700,500 ; Graphics command HAS to come first for (following) LoadImage to function

Include "typedefs.bb"
; TODO why cant I do this;;; Include "functions.bb"
Include "maps.bb"
Include "events.bb"
Include "playermovement.bb"
Include "helpers.bb"



AppTitle "The Legend of Lamy"
SetBuffer BackBuffer()


 
Dim NPCs.NPC(100) ;the dot means something entirely different here than in other languages
					; basically the dot means "of Type ..."
NPCs(0) = New NPC
NPCs(0)\name$ = "John"




; Start


; GRAFIKBEFEHLE


; TODO make coordinates relative, so the game would technically be scalable

; apparently it's not possible to make Global Constants, so here we go
Const RECHTS = 1
Const UNTEN = 2
Const LINKS = 3


Global playerBoxX Global playerBoxY
Global playerBoxWidth = 30
Global playerBoxHeight = 30
;Global playerTurned = 0 ; how many degrees the player is turned, starting with 0 being "12 oclock"
Global playerMovementX = 0 Global playerMovementY = 0
Global speed = 3

; SPAWN where?
Global map.Map = bar ; start on home map for now
Global x=250
Global y=250 
Global prevPlayerRotation = 0 Global currPlayerRotation = 0



Global playerImage = LoadImage("player.bmp")
MaskImage playerImage, 255, 255, 255 ; make white transparent instead of black

HandleImage playerImage, 15, 7 ; set handle to center cause of rotation n stuff

Color 0,0,0
ClsColor 255, 255, 255

Global DebugMode = True


Repeat

	;If map = road Then Stop

	Cls
	
	RenderMap(map)

	GetPlayerMovement()	
	MovePlayer()
	; TODO add sound effect to walking... like footsteps
	
	
	; If collide or out of bounds, move player back to pre-collision
	; Or - and hear me out - we could just draw a frame and leave holes in it and whenever the player leaves
		; bounds we know he's entering another map
	; TODO draw the maps out fully and then start deleting to create "sub" maps.
		; collision map >> delete "walkable" objects like grass, stools, walkways, ...
		; event map >> delete everything that can't interact (leaving maybe only characters)
		; basically create layers by deleting from the original "graphic"
		; OR
		; use something fancy like Gimp to layer them and then export all layers into individual files
	If PlayerCollidingWith(map) Then
		UndoMovePlayer()
	EndIf	
	
	DrawPlayer()
	; FOR DEBUGGING: draw collision box: Rect playerBoxX, playerBoxY, playerBoxWidth, playerBoxHeight, 0
	If DebugMode Then DebugDraw()
	
	CheckEvents() ; because we want the dialogue boxes to be displayed above debug boxes n stuff
	
	Flip()

Until KeyDown(1) = 1
End








; Function Defs

Function DebugDraw()
	Color 255,0,0
	
	; TODO loop through event list total or "local" map event dim if we ever create one
	For i = 0 To 10
		If map\EventAreas[i] <> Null Then
			Rect map\EventAreas[i]\x, map\EventAreas[i]\y, map\EventAreas[i]\width, map\EventAreas[i]\height, 0
			Text map\EventAreas[i]\x, map\EventAreas[i]\y, map\EventAreas[i]\name$
		Else
			Exit
		EndIf
	Next
	
	; show player bounding box
	Rect playerBoxX, playerBoxY, playerBoxWidth, playerBoxHeight, 0
	
	; show mouse position for easier coordinate setting
	Text 0,0, "Pos: " + MouseX() + ", " + MouseY()
	
	Color 0,0,0
End Function


; TODO maybe replace with a color based system? That way events can be drawn right into map and correlated by color hex code /rgb
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
	HandleExits()
	
	; user is trying to interact, so let's check if he's in an event area
	If KeyDown(28) Then
		; TODO loop through event list total or "local" map event dim if we ever create one
		For i = 0 To 10 ; TODO obviously this should be a more dynamic size... though I don't think that's possible in BB
			If map\EventAreas[i] <> Null Then
				If RectsOverlap(playerBoxX, playerBoxY, playerBoxWidth, playerBoxHeight,   map\EventAreas[i]\x, map\EventAreas[i]\y, map\EventAreas[i]\width, map\EventAreas[i]\height) Then
					ShowDialogue(map\EventAreas[i]\e)
				EndIf	
			EndIf				
		Next
	EndIf
	
	
End Function


Function DrawPlayer()
	RotatePlayerImage()
	DrawImage playerImage, x, y
End Function


Function RotatePlayerImage()
	RotateImage playerImage, prevPlayerRotation - currPlayerRotation
End Function


