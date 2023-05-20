; Include "other stuff.bb"


; SETUP

Graphics 640,480
AppTitle "The Legend of Lamy"
SetBuffer BackBuffer()

Global StoryState = 0, Inventory = 0

inty% = 3
stringy$ = "You wake up drenched in sweat in your bed"


Type NPC
	Field name$
	Field image
	Field position
	Field states ; first meeting is state 0, etc...
End Type

;Global mine.MyType Dim all_mine.MyType( 100 ) 
Global Dim NPCs.NPX(100)

NPCs(0)(0)\name$ = "John"


; Start

Name$ = Input$("Geben Sie Ihren Namen ein: ")

.label

Print CountGfxDrivers()


; GRAFIKBEFEHLE

; DrawBlock, TileBlock, CopyImage: Diese befehle arbeiten etwas schneller als Image-Befehle, weil sie den ursprünglichen Hintergrund nicht erhalten müssen
; SaveImage, SaveBuffer
; ViewPort, Origin
; Text = Grafikmodus anstatt Print
; CreateBank Speicherbaenke fuer andere grosse dateien, um sie zuerst in den Arbeitsspeicher zu laden und dann erst schritt fuer schritt auszulesen, e.g. leveldateien, ...
; optimize collision: only check if bounding boxes overlap, only if they do repeat check more granularly


Repeat
Cls
Rect X,Y, 100,100
Flip(False) ; awesome hack from some dude online, turns off 60fps cap
If KeyDown(200) = 1 Then Y = Y - 1
If KeyDown(208) = 1 Then Y = Y + 1
If KeyDown(205) = 1 Then X = X + 1
If KeyDown(203) = 1 Then X = X - 1
Until KeyDown(1) = 1


Plot 250,250

For B=0 To 39
For H=0 To 29
Rect B*16,H*16,16,16,0
Next
Next
SeedRnd MilliSecs()
For I=1 To 100
Rect Rand(0,39) * 16, Rand(0,29) * 16, 16,16, 1
Next
For I=1 To 100 Step 5
Oval 100 + I, 100 + I, 100+I, 100+I, 0
Next

bild = LoadImage("rakete.png")
ScaleImage bild, 0.5, 0.5
DrawImage bild, 100, 100

GrabImage bild, 0, 0
DrawImage bild, 400, 100

Flip

If Name$ = "Pat" Then
  Print "Hallo euer Hoheit!"
Else
  Print "Hallo " + Name$ + "!"
EndIf

Print Doppelt(2) 

; crates endless loop Goto label


WaitKey
End


; possible subroutines come here, after End



Function Doppelt(X)
  Return X * 2 * Factor
End Function

Data "Alien", 20, 39, 4.5
; did I use data back then? looks helpful
