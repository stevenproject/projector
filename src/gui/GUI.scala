package gui

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.effect.BlendMode.{Green, Red}
import scalafx.scene.{Group, Scene}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
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
  val top: Rectangle = new Rectangle{ //top
    width = 700.0
    height = 10.0
    x = 0.0
    y = 0.0
    fill = Color.Black
  }
  val right: Rectangle = new Rectangle{ //right
      width = 10.0
      height = 700.0
      x = 690.0
      y = 0.0
      fill = Color.Black
  }
  val left: Rectangle = new Rectangle{
      width = 10.0
      height = 700.0
      x = 0.0
      y = 0.0
      fill = Color.Black
  }
  val bot: Rectangle = new Rectangle{
      width = 700.0
      height = 10.0
      x = 0.0
      y = 690.0
      fill = Color.Black
  }
  val defeat: Text = new Text {
    text = "Score for this problem: 0.0"
    style = "-fx-font-size: 96 pt"
    fill = Color.Black
    x = 0.0
    y = 350.0
  }
  def addbounds(bound: Rectangle): Unit = {
    scenestuff.children.add(bound)
  }

  scenestuff.children.add(Player)
  addbounds(top)
  addbounds(right)
  addbounds(left)
  addbounds(bot)
  scenestuff.children.add(defeat)
  def moveRight(): Unit = {
    Player.translateX.value += playerSpeedx
      if(Player.intersects(right.getBoundsInLocal)){
        scenestuff.getChildren.remove(Player)
        scenestuff.children.add(defeat)
      }
    }
  def moveLeft(): Unit = {
    Player.translateX.value -= playerSpeedx
    if(Player.intersects(left.getBoundsInLocal)){
      scenestuff.getChildren.remove(Player)
      scenestuff.children.add(defeat)
    }
  }
  def moveBot(): Unit = {
    Player.translateY.value += playerSpeedx
    if(Player.intersects(bot.getBoundsInLocal)){
      scenestuff.getChildren.remove(Player)
    }
  }
  def moveTop(): Unit = {
    Player.translateY.value -= playerSpeedx
    if(Player.intersects(top.getBoundsInLocal)){
      playerSpeedx = 0
      playerSpeedy = 0
      scenestuff.getChildren.remove(Player)
    }
  }
  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => moveTop()
      case "A" => moveLeft()
      case "S" => moveBot()
      case "D" => moveRight()
      case _ => println("wrong button sir")
    }
  }

  this.stage = new PrimaryStage {
    this.title = "lord help us!"
    scene = new Scene(1280, 960){
      fill = Color.White
      content = scenestuff
      }
    addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
    }
  }