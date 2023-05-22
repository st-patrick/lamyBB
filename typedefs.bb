; ok so the problems with custom types is that they all live in the same list
; so if I want to be able to seperate them without having to go through globally ALL instances of the same type
; I will need to rather use Dims or so
Type NPC
	Field name$
	Field image
	Field position
	Field states ; first meeting is state 0, etc...
End Type

Type EventArea
	Field name$
	Field x
	Field y
	Field width
	Field height
	Field e.Event
	Field map.Map
	Field enabled ; first meeting is state 0, etc...
End Type

Type Event
	Field name$
	Field ea.EventArea
	Field dialogue$
	Field enabled ; first meeting is state 0, etc...
End Type