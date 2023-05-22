; lamy maps

; this would also allow use to easily have a "overview" for debugging, where maps are shown glued together
; and zoomed out
Type Map
	Field bild
	Field links.Map
	Field rechts.Map
	Field unten.Map
	Field oben.Map
End Type

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


; now to connect it all
home\rechts = blank

blank\links = home
blank\unten = neighborhood

neighborhood\oben = blank
neighborhood\links = bar

bar\rechts = neighborhood