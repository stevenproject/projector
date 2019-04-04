package gui

import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.input.MouseEvent
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.{Group, Scene}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.text.Text

object GUI extends JFXApp {

  var scenestuff: Group = new Group {}
  val DAMNYOUHW5: Image = new Image("/gui/HARTLOFF.png",true)
  val jesse: Image = new Image("/gui/jessewhat.jpg",true)
  val iv: ImageView = new ImageView(DAMNYOUHW5)
  val h: ImageView = new ImageView(jesse)
  h.x = 500
  h.y = 500
  var playerSpeedx = 10
  var playerSpeedy = 10
  val rx = new scala.util.Random
  val Player: Circle = new Circle {
    centerX = 10 + rx.nextInt(1004)
    centerY = 10 + rx.nextInt(748)
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
  var WALL: List[Rectangle] = List()

  WALL = WALL :+ top
  WALL = WALL :+ left
  WALL = WALL :+ bot
  WALL = WALL :+ right

  val defeat: Text = new Text {
    text = "Score for this problem: 0.0"
    style = "-fx-font-size: 96 pt"
    fill = Color.Black
    x = 0.0
    y = 100.0
  }
  val victory: Text = new Text {
    text = "Score for this problem: 50.0"
    style = "-fx-font-size: 96 pt"
    fill = Color.Black
    x = 0.0
    y = 100.0
  }
  def addbounds(bounds: List[Rectangle]): Unit = {
    for (bound <- bounds)
    scenestuff.children.add(bound)
  }

  scenestuff.children.add(iv)
  scenestuff.children.add(Player)
  addbounds(WALL)
  scenestuff.children.add(h)

  def collision(bounds: List[Rectangle]): Boolean = {
    var detect: Boolean = false
    for (bound <- bounds){
      if (Player.getBoundsInParent.intersects(bound.getBoundsInLocal)){
        detect = true
      }
    }
    detect
  }
  def hartloffmoved(bounds: List[Rectangle]): Unit = {
    if (Player.getBoundsInParent.intersects(h.getBoundsInLocal)){
      h.translateX.value += playerSpeedx
      for (bound <- bounds){
        if (h.getBoundsInParent.intersects(bound.getBoundsInLocal)){
          scenestuff.getChildren.remove(Player)
          scenestuff.getChildren.remove(h)
          scenestuff.children.add(victory)
        }
      }
    }
  }
  def hartloffmovew(bounds: List[Rectangle]): Unit = {
    if (Player.getBoundsInParent.intersects(h.getBoundsInLocal)){
      h.translateY.value -= playerSpeedx
      for (bound <- bounds){
        if (h.getBoundsInParent.intersects(bound.getBoundsInLocal)){
          scenestuff.getChildren.remove(Player)
          scenestuff.getChildren.remove(h)
          scenestuff.children.add(victory)
        }
      }
    }
  }
  def hartloffmovea(bounds: List[Rectangle]): Unit = {
    if (Player.getBoundsInParent.intersects(h.getBoundsInLocal)){
      h.translateX.value -= playerSpeedx
      for (bound <- bounds){
        if (h.getBoundsInParent.intersects(bound.getBoundsInLocal)){
          scenestuff.getChildren.remove(Player)
          scenestuff.getChildren.remove(h)
          scenestuff.children.add(victory)
        }
      }
    }
  }
  def hartloffmoves(bounds: List[Rectangle]): Unit = {
    if (Player.getBoundsInParent.intersects(h.getBoundsInLocal)){
      h.translateY.value += playerSpeedx
      for (bound <- bounds){
        if (h.getBoundsInParent.intersects(bound.getBoundsInLocal)){
          scenestuff.getChildren.remove(Player)
          scenestuff.getChildren.remove(h)
          scenestuff.children.add(victory)
        }
      }
    }
  }
  def moveRight(): Unit = {
    Player.translateX.value += playerSpeedx
    hartloffmoved(WALL)
    if (collision(WALL)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveLeft(): Unit = {
    Player.translateX.value -= playerSpeedx
    hartloffmovea(WALL)
    if (collision(WALL)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveBot(): Unit = {
    Player.translateY.value += playerSpeedx
    hartloffmoves(WALL)
    if (collision(WALL)) {
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }

  def moveTop(): Unit = {
    Player.translateY.value -= playerSpeedx
    hartloffmovew(WALL)
    if (collision(WALL)) {
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

  def MouseMovex (x: Int): Unit = {
    if (10 < x && x < 1024){
      if (Player.centerX.value < x) {
        Player.centerX.value += 1
        iv.translateX.value += 1
        MouseMovex(x)
      }
      else if (Player.centerX.value > x) {
        Player.centerX.value -= 1
        iv.translateX.value -= 1
        MouseMovex(x)
      }
      else {
      }
      if (collision(Player.translateX.value, Player.translateY.value, WALL)){
        scenestuff.getChildren.remove(Player)
        scenestuff.children.add(defeat)
      }
    }
    else {
      println("out of x-range")
    }
  }

    def MouseMovey (y: Int): Unit = {
      if (110 < y && y < 768) {
        if (Player.centerY.value < y) {
          Player.centerY.value += 1
          iv.translateY.value += 1
          MouseMovey(y)
        }
        else if (Player.centerY.value > y) {
          Player.centerY.value -= 1
          iv.translateY.value -= 1
          MouseMovey(y)
        }
        else {
        }
        if (collision(Player.translateX.value, Player.translateY.value, WALL)){
          scenestuff.getChildren.remove(Player)
          scenestuff.children.add(defeat)
        }
      }
      else{
        println("out of y-range")
      }
    }

  def MouseMove(x: Double, y: Double): Unit = {
    val xi: Int = x.toInt
    val yi: Int = y.toInt
    MouseMovex(xi)
    MouseMovey(yi)
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
}