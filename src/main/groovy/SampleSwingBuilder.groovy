/*
* Groovy sample logic to try to build a GUI composed of bindable variables plus a formatted swing gui layout
*/

import javax.swing.UIManager
import groovy.swing.SwingBuilder
import groovy.beans.Bindable
import javax.swing.*
import java.awt.*

class MyModel {
   @Bindable int count = 0
   @Bindable String name = "Hello Fred";
}

def model = new MyModel()

new SwingBuilder().edt 
{
  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel")

  frame(title: 'Sample SwingBuilder Frame', size: [300, 200], locationRelativeTo: null, show: true, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
    gridLayout(cols: 2, rows: 2)
    label(text: bind(source:model, sourceProperty:'name' ))
    label(text: bind(source: model, sourceProperty: 'count', converter: { v ->  v? "Button Clicked $v times": ''}))
    button('Click me!', actionPerformed: { model.count++ })
  } // end of frame

} // end of edt

