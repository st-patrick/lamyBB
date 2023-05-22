Function GetPlayerMovement()
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
		currPlayerRotation = -90
	EndIf
	If KeyDown(203) = 1 Then ; LEFT
		playerMovementX = -speed
		currPlayerRotation = 90
	EndIf
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

