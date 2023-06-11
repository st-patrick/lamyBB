bar.Map = New Map
bar\bild= LoadImage("map004.bmp")
MaskImage bar\bild, 255, 255, 255 ; make white transparent instead of black
bar\spawnX = 650


; DOORS

bar\doors[0] = New Door
bar\doors[0]\hingeX = 525
bar\doors[0]\hingeY = 205


; EVENTS

bar\EventAreas[0] = New EventArea
bar\EventAreas[0]\name$ = "Talk to the barkeeper"
bar\EventAreas[0]\x = 500 ; TODO show mouse pointer position in pixel for easy event creation
bar\EventAreas[0]\y = 25
bar\EventAreas[0]\width = 100
bar\EventAreas[0]\height = 100
bar\EventAreas[0]\map = bar
bar\EventAreas[0]\e = New Event
bar\EventAreas[0]\e\dialogue$ = "Welcome to the bar"


bar\EventAreas[1] = New EventArea
bar\EventAreas[1]\name$ = "statue"
bar\EventAreas[1]\x = 210 ; TODO show mouse pointer position in pixel for easy event creation
bar\EventAreas[1]\y = 300
bar\EventAreas[1]\width = 100
bar\EventAreas[1]\height = 100
bar\EventAreas[1]\map = bar
bar\EventAreas[1]\e = New Event
bar\EventAreas[1]\e\dialogue$ = "A statue of a woman in a dress holding a sword"

bar\EventAreas[2] = New EventArea
bar\EventAreas[2]\name$ = "lake"
bar\EventAreas[2]\x = 0 ; TODO show mouse pointer position in pixel for easy event creation
bar\EventAreas[2]\y = 80
bar\EventAreas[2]\width = 150
bar\EventAreas[2]\height = 400
bar\EventAreas[2]\map = bar
bar\EventAreas[2]\e = New Event
bar\EventAreas[2]\e\dialogue$ = "A nice lake, shining beautifully in azure under the sun"