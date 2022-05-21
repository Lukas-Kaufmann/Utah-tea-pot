# Rendering Utah teapot with pipes and filters

### Starten

``./gradlew run`` ausführen.

## Designentscheidungen

### Interfaces wiederverwendbar für beide Pipelines
Für die Steps welche mit einer 1 zu 1 umwandlung von Faces machbar waren, wurden diese Vorschriften in ein separates 
Interface (ITransformer) ausgelagert und als Komponente in eine generische IFilter-implementierung übergeben, dieser Filter 
kümmert sich um die Reihenfolge der Operationen.

Die anderen Filter (Source, Sink und Depthsorting) implementieren IFilter direkt. 

[Klassendiagram](./class-diagram.png)

### Farbinformation über Vererbung

Farben werden in den Pipelines in einer Unterklasse von Face übergeben.

### Depthsorting in beiden Pipelines
Obwohl es in der Aufgabenstellung heißt Depth-sorting ist nur bei einer Art der Pipelines möglich haben wir es in beiden implemntiert.
Da auch beide Arten relativ gleich implementiert wurden, alle Ergebnisse der vorherigen Schritte sammeln bis das letzte Face
ankommt und dann die Faces in der sortierten Reihenfolge weitergeben, ist uns nicht ganz klar warum es nur in einer Pipeline möglich sein sollte.
Falls es bei einer der Pipelines eine einfachere Möglichkeit gibt sind wir nicht draufgekommen.