Dim EventAreas.EventArea(100)

EventAreas(0) = New EventArea
EventAreas(0)\name$ = "Talk to the barkeeper"
EventAreas(0)\x = 500 ; TODO show mouse pointer position in pixel for easy event creation
EventAreas(0)\y = 25
EventAreas(0)\width = 100
EventAreas(0)\height = 100
EventAreas(0)\map = bar
EventAreas(0)\e = New Event
EventAreas(0)\e\dialogue$ = "Welcome to the bar"


EventAreas(1) = New EventArea
EventAreas(1)\name$ = "statue"
EventAreas(1)\x = 210 ; TODO show mouse pointer position in pixel for easy event creation
EventAreas(1)\y = 300
EventAreas(1)\width = 100
EventAreas(1)\height = 100
EventAreas(1)\map = bar
EventAreas(1)\e = New Event
EventAreas(1)\e\dialogue$ = "A statue of a woman in a dress holding a sword"

EventAreas(2) = New EventArea
EventAreas(2)\name$ = "lake"
EventAreas(2)\x = 0 ; TODO show mouse pointer position in pixel for easy event creation
EventAreas(2)\y = 80
EventAreas(2)\width = 150
EventAreas(2)\height = 400
EventAreas(2)\map = bar
EventAreas(2)\e = New Event
EventAreas(2)\e\dialogue$ = "A nice lake, shining beautifully in azure under the sun"

; TODO turn this into data objects or at least spare the work of manually incrementing the numbers somehow

EventAreas(3) = New EventArea
EventAreas(3)\name$ = "roadblock"
EventAreas(3)\x = 200 ; TODO show mouse pointer position in pixel for easy event creation
EventAreas(3)\y = 170
EventAreas(3)\width = 300
EventAreas(3)\height = 100
EventAreas(3)\map = road
EventAreas(3)\e = New Event
EventAreas(3)\e\dialogue$ = "Halt! We can not allow you to pass. There have been sightings of witches and monsters. Turn around now."