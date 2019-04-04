package gui

import scala.collection.mutable.ListBuffer

class PlayerList(var ActivePlayers: ListBuffer[String]){

  def AddPlayers(Name: String): Unit = {
    this.ActivePlayers = this.ActivePlayers :+ Name
  }

  def RemovePlayers(Name: String): Unit = {
    this.ActivePlayers -= Name
  }

  def DeclareWinner(): Unit = {
    if (this.ActivePlayers.length == 1){
      println("The winner is " + this.ActivePlayers.head)
    }
  }
}
