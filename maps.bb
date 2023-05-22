; lamy maps

; TODO this could be a good use for data... to write & edit maps easily
Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful, especially in other files so I can just import instead of readfile etc.

; this would also allow use to easily have a "overview" for debugging, where maps are shown glued together
; and zoomed out
Type Map
	Field bild
	Field links.Map
	Field rechts.Map
	Field unten.Map
	Field oben.Map
	Field spawnX
	; TODO something like this: Field Dim events.Event(10)
End Type

; TODO find a system where I can resize maps and resolutions without having to edit image files... somehow
	;options
	; 1) have a continuous map where you just move around, so the "camera" only shows you a certain perspective
		; but the map itself doesn't have fixed ratio or anything like that

; for now use variables but in the long run, prob necessary to organize maps better... by name or what not
home.Map = New Map
home\bild= LoadImage("map001.bmp")
MaskImage home\bild, 255, 255, 255 ; make white transparent instead of black

blank.Map = New Map
blank\bild= LoadImage("map002.bmp")
MaskImage blank\bild, 255, 255, 255 ; make white transparent instead of black

neighborhood.Map = New Map
neighborhood\bild= LoadImage("map003.bmp")
MaskImage neighborhood\bild, 255, 255, 255 ; make white transparent instead of black

bar.Map = New Map
bar\bild= LoadImage("map004.bmp")
MaskImage bar\bild, 255, 255, 255 ; make white transparent instead of black
bar\spawnX = 650

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
