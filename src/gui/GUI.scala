package gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}

object GUI extends JFXApp {

  var OurPlayer: Group = new Group {}

  val Player: Circle = new Circle {
    centerX = Math.random() * 1280
    centerY = Math.random() * 960
    radius = 20
    fill = Color.Beige
  }



  val background = new Rectangle() {
    width = 1280
    height = 960
    translateX = 0.0
    translateY = 0.0
    fill = Color.Yellow
  }

  this.stage = new PrimaryStage {
    this.title = "lord help us"
    scene = new Scene(1280, 960){
      fill = Color.White
      content = new HBox{
        children = List(
        new Rectangle{ //top
          width = 700.0
          height = 10.0
          translateX = 0.0
          translateY = 0.0
          fill = Color.Black
        },
        new Rectangle{ //right
          width = 10.0
          height = 700.0
          translateX = -700.0
          translateY = 0.0
          fill = Color.Black
        },
          new Rectangle{
            width = 10.0
            height = 700.0
            translateX = -10.0
            translateY = 0.0
            fill = Color.Black
          },
          new Rectangle{
            width = 715.0
            height = 10.0
            translateX = -725.0
            translateY = 700.0
          }
        )
      }
    }
  }


}
