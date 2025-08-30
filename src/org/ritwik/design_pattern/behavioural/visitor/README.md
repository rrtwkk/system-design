# Visitor Pattern

## UML Class Diagram

```
┌─────────────────────┐              ┌─────────────────────┐
│  DocumentVisitor    │              │  DocumentElement    │
│   <<interface>>     │              │   <<interface>>     │
├─────────────────────┤              ├─────────────────────┤
│+ visit(TextElement) │              │+ accept(DocumentVisitor)│
│+ visit(ImageElement)│              └─────────────────────┘
│+ visit(TableElement)│                        △
└─────────────────────┘                        │
          △                           ┌────────┼────────┐
          │                           │        │        │
    ┌─────┴─────┐              ┌─────────┐ ┌─────────┐ ┌─────────┐
┌─────────────┐ ┌─────────────┐ │TextElement│ │ImageElement│ │TableElement│
│HTMLExportVisitor│ │PDFExportVisitor│ ├─────────┤ ├─────────┤ ├─────────┤
├─────────────┤ ├─────────────┤ │- text: String│ │- imagePath: String│ │- rows: int│
│- html: StringBuilder│ │- pdf: StringBuilder│ ├─────────┤ │- width: int│ │- columns: int│
├─────────────┤ ├─────────────┤ │+ TextElement(String)│ │- height: int│ ├─────────┤
│+ visit(TextElement)│ │+ visit(TextElement)│ │+ accept(DocumentVisitor)│ ├─────────┤ │+ TableElement(int,int)│
│+ visit(ImageElement)│ │+ visit(ImageElement)│ │+ getText(): String│ │+ ImageElement(String,int,int)│ │+ accept(DocumentVisitor)│
│+ visit(TableElement)│ │+ visit(TableElement)│ └─────────┘ │+ accept(DocumentVisitor)│ │+ getRows(): int│
│+ getHTML(): String│ │+ getPDF(): String│              │+ getters...│ │+ getColumns(): int│
└─────────────┘ └─────────────┘              └─────────┘ └─────────┘

┌─────────────────────┐
│      Document       │
│  (Object Structure) │
├─────────────────────┤
│- elements: List<DocumentElement>│
├─────────────────────┤
│+ addElement(DocumentElement)│
│+ accept(DocumentVisitor)│
└─────────────────────┘
```

## Key Components
- **Visitor**: DocumentVisitor interface
- **Concrete Visitors**: HTMLExportVisitor, PDFExportVisitor
- **Element**: DocumentElement interface
- **Concrete Elements**: TextElement, ImageElement, TableElement
- **Object Structure**: Document class

## Purpose
Defines new operations on elements of an object structure without changing the element classes themselves.
