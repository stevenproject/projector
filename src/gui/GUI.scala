package gui

import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.input.MouseEvent
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.text.Text

object GUI extends JFXApp {

  var scenestuff: Group = new Group {}
  var playerSpeedx = 10
  var playerSpeedy = 10
  val Player: Circle = new Circle {
    centerX = 200
    centerY = 200
    radius = 20
    fill = Color.HotPink
  }
  val top: Rectangle = new Rectangle {
    width = 1024.0
    height = 10.0
    x = 0.0
    y = 100.0
    fill = Color.Black
  }
  val right: Rectangle = new Rectangle {
    width = 10.0
    height = 768.0
    x = 1014.0
    y = 100.0
    fill = Color.Black
  }
  val left: Rectangle = new Rectangle {
    width = 10.0
    height = 768.0
    x = 0.0
    y = 100.0
    fill = Color.Black
  }
  val bot: Rectangle = new Rectangle {
    width = 1024.0
    height = 10.0
    x = 0.0
    y = 858.0
    fill = Color.Black
  }
  val defeat: Text = new Text {
    text = "Score for this problem: 0.0"
    style = "-fx-font-size: 96 pt"
    fill = Color.Black
    x = 0.0
    y = 100.0
  }

  def addbounds(bound: Rectangle): Unit = {
    scenestuff.children.add(bound)
  }

  scenestuff.children.add(Player)
  addbounds(top)
  addbounds(right)
  addbounds(left)
  addbounds(bot)

  def moveRight(): Unit = {
    Player.translateX.value += playerSpeedx
    if (Player.getBoundsInParent.intersects(right.getBoundsInLocal)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveLeft(): Unit = {
    Player.translateX.value -= playerSpeedx
    if (Player.getBoundsInParent.intersects(left.getBoundsInParent)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveBot(): Unit = {
    Player.translateY.value += playerSpeedx
    if (Player.getBoundsInParent.intersects(bot.getBoundsInParent)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveTop(): Unit = {
    Player.translateY.value -= playerSpeedx
    if (Player.getBoundsInParent.intersects(top.getBoundsInParent)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def keyboardMove(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => moveTop()
      case "A" => moveLeft()
      case "S" => moveBot()
      case "D" => moveRight()
      case _ => println("wrong button sir")
    }
  }
  def MouseMovex (x: Double): Unit = {
    if (Player.translateX.value < x) {
      Player.translateX.value += x/100
      MouseMovex(x)
    }
    else if (Player.translateX.value > x) {
      Player.translateX.value -= x/100
      MouseMovex(x)
    }
  }
    def MouseMovey (y: Double): Unit = {
      if (Player.translateY.value < y) {
        Player.translateY.value += y/100
        MouseMovey(y)
      }
      else if (Player.translateY.value > y) {
        Player.translateY.value -= y/100
        MouseMovey(y)
      }
  }
  def MouseMove(x: Double, y: Double): Unit = {
    MouseMovex(x)
    MouseMovey(y)
  }

  this.stage = new PrimaryStage {
    this.title = "Submit to Autograder"
    scene = new Scene(1280, 960) {
      fill = Color.White
      content = scenestuff
    }
    addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyboardMove(event.getCode))
    addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => MouseMove(event.getX, event.getY))
  }
  val update: Long => Unit = (time: Long) => {
    4
  }

  // Start Animations. Calls update 60 times per second (takes update as an argument)
  AnimationTimer(update).start()
}