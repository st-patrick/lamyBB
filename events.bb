

; TODO turn this into data objects or at least spare the work of manually incrementing the numbers somehow

road\EventAreas[0] = New EventArea
road\EventAreas[0]\name$ = "roadblock"
road\EventAreas[0]\x = 200 ; TODO show mouse pointer position in pixel for easy event creation
road\EventAreas[0]\y = 170
road\EventAreas[0]\width = 300
road\EventAreas[0]\height = 100
road\EventAreas[0]\map = road
road\EventAreas[0]\e = New Event
road\EventAreas[0]\e\dialogue$ = "Halt! We can not allow you to pass. There have been sightings of witches and monsters. Turn around now."