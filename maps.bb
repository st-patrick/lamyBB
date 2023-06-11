; lamy maps

; TODO this could be a good use for data... to write & edit maps easily
Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful, especially in other files so I can just import instead of readfile etc.

; this would also allow use to easily have a "overview" for debugging, where maps are shown glued together
; and zoomed out
Type Map
	Field bild ; the LoadImage resource
	Field bildDecoration ; a LoadImage resource that will be displayed but not collide
	Field links.Map ;the Map to the left of this map
	Field rechts.Map  ; the Map to the right of this map
	Field unten.Map  ; the Map to the bottom of this map
	Field oben.Map ; the Map to the top of this map
	Field spawnX ; If this is set, when going to another top or bottom map, player will spawn at this x coordinate
	Field spawnY ; same as SpawnX but with Y axis and left or right map
	Field doors.Door[1]
	Field EventAreas.EventArea[10]
	; TODO something like this: Field Dim events.Event(10)
End Type

; TODO find a system where I can resize maps and resolutions without having to edit image files... somehow
	;options
	; 1) have a continuous map where you just move around, so the "camera" only shows you a certain perspective
		; but the map itself doesn't have fixed ratio or anything like that


blank.Map = New Map
blank\bild= LoadImage("map002.bmp")
MaskImage blank\bild, 255, 255, 255 ; make white transparent instead of black

neighborhood.Map = New Map
neighborhood\bild= LoadImage("map003.bmp")
MaskImage neighborhood\bild, 255, 255, 255 ; make white transparent instead of black

Include "map_bar.bb"
Include "map_home.bb"


; DOORS

bar\doors[0] = New Door
bar\doors[0]\hingeX = 525
bar\doors[0]\hingeY = 205





road.Map = New Map
road\bild= LoadImage("map005.bmp")
MaskImage road\bild, 255, 255, 255 ; make white transparent instead of black
road\spawnX = 300

;TODO loop through all maps and mask them
;TODO loop through numbers 1 to 100 to load all maps basically ... although they would be better off named


; now to connect it all
home\rechts = blank

blank\links = home
blank\unten = neighborhood

neighborhood\oben = blank
neighborhood\links = bar
neighborhood\unten = road

bar\rechts = neighborhood
bar\unten = road

road\oben = bar



; ======== content ===========


Function RenderMap(map.Map)
	DrawImage map\bild, 0, 0
	If map\bildDecoration <> 0 Then 
		DrawImage map\bildDecoration, 0, 0
	EndIf
	
	Color 0,255,0
	counter = 0
	While (map\doors[counter] <> Null)
		Rect map\doors[counter]\hingeX, map\doors[counter]\hingeY, 50, 8
		counter = counter + 1
	Wend
	Color 0,0,0
	
End Function


Function HandleExits()
	If x > 700 Then
		map = map\rechts
		x = 20
	Else If x < 0 Then
		map = map\links
		x = 680
	Else If y > 500 Then
		map = map\unten	
		y = 20		
		If map\spawnX > 0 Then x = map\spawnX
	Else If y < 0 Then
		map = map\oben	
		y = 480		
		If map\spawnX > 0 Then x = map\spawnX	
	EndIf
End Function

Function PlayerCollidingWith(map.Map)
	isCollidingWithBaseImage = ImageRectCollide (map\bild,0,0,0,playerBoxX,playerBoxY,playerBoxWidth ,playerBoxHeight )
	
	counter = 0
	isCollidingWithDoor = False
	While (map\doors[counter] <> Null)
		isCollidingWithDoor = isCollidingWithDoor Or RectsOverlap(map\doors[counter]\hingeX, map\doors[counter]\hingeY, 50, 8, playerBoxX,playerBoxY,playerBoxWidth ,playerBoxHeight)
		counter = counter + 1
	Wend
	
	Return isCollidingWithBaseImage Or isCollidingWithDoor 
End Function