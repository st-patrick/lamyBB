; for now use variables but in the long run, prob necessary to organize maps better... by name or what not
home.Map = New Map
home\bild = LoadImage("map001.bmp")
MaskImage home\bild, 255, 255, 255 ; make white transparent instead of black
home\bildDecoration = LoadImage("map001-decoration.bmp")
MaskImage home\bildDecoration, 255, 255, 255 ; make white transparent instead of black


; EVENTS

home\EventAreas[0] = New EventArea
home\EventAreas[0]\name$ = "Talk to neighbor"
home\EventAreas[0]\x = 100 ; TODO show mouse pointer position in pixel for easy event creation
home\EventAreas[0]\y = 400
home\EventAreas[0]\width = 100
home\EventAreas[0]\height = 100
home\EventAreas[0]\map = bar
home\EventAreas[0]\e = New Event
home\EventAreas[0]\e\dialogue$ = "What are you doing in my house?"