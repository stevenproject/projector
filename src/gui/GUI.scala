package gui

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.effect.BlendMode.{Green, Red}
import scalafx.scene.{Group, Scene}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}

object GUI extends JFXApp {

  var scenestuff: Group = new Group {}

  val Player: Circle = new Circle {
    centerX = 200
    centerY = 200
    radius = 20
    fill = Color.BlueViolet
  }
  val bounds: List[Rectangle] = List(
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
      translateX = 690.0
      translateY = 0.0
      fill = Color.Black
    },
    new Rectangle{
      width = 10.0
      height = 700.0
      translateX = 0.0
      translateY = 0.0
      fill = Color.Red
    },
    new Rectangle{
      width = 700.0
      height = 10.0
      translateX = 0.0
      translateY = 690.0
      fill = Color.BlueViolet
    }
  )
  val background = new Rectangle() {
    width = 1280
    height = 960
    translateX = 0.0
    translateY = 0.0
    fill = Color.Yellow
  }
  def addbounds(bound: List[Rectangle]): Unit = {
    for (bond <- bound) {
      scenestuff.children.add(bond)
    }
  }
    addbounds(bounds)
    scenestuff.children.add(Player)
  this.stage = new PrimaryStage {
    this.title = "lord help us!"
    scene = new Scene(1280, 960){
      fill = Color.White
      content = scenestuff
      }
    }
  }