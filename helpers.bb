Function ShowDialogue(e.Event)
	Color 0,0,255
	Rect 50,350, 600, 100
	Color 255,255,255
	
	txt$ = e\dialogue$
	partial$ = ""
	offset = 0
	counter = 0
	While(Len(txt$) > 0)
		partial$ =  Left(txt$, 60)
		Text 80, 380 + 20*counter , partial$
		txt$ = Replace(txt$, partial$, "")
		counter = counter + 1
	Wend
	
	Color 0,0,0
	WaitKey
End Function