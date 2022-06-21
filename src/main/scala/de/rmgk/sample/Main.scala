package de.rmgk.sample

import com.gluonhq.attach.display.DisplayService
import com.gluonhq.attach.util.Platform
import com.gluonhq.charm.glisten.application.AppManager
import com.gluonhq.charm.glisten.control.AppBar
import com.gluonhq.charm.glisten.control.FloatingActionButton
import com.gluonhq.charm.glisten.mvc.View
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon
import com.gluonhq.charm.glisten.visual.Swatch
import javafx.application.Application
import javafx.geometry.Dimension2D
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.stage.Stage
import com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW
import javafx.event.ActionEvent
import rescala.default._

object Main:
  def main(args: Array[String]): Unit =
    System.setProperty("prism.forceGPU", "true")
    System.setProperty("prism.verbose", "true")
    Application.launch(classOf[Main], args: _*)


class Main extends Application {
  final private val appManager = AppManager.initialize(this.postInit)
  override def init(): Unit = {

    val evt = Evt[String]()
    evt.observe(println)

    appManager.addViewFactory(
      HOME_VIEW,
      () => {
        def foo() = {
          val fab =
            new FloatingActionButton(
              MaterialDesignIcon.SEARCH.text,
              (e: ActionEvent) => evt.fire(s"hahah!")
            )
          val imageView = new ImageView(new Image(classOf[Main].getResourceAsStream("openduke.png")))
          imageView.setFitHeight(200)
          imageView.setPreserveRatio(true)
          val label = new Label("Hello, My Gluon Application!")
          val root  = new VBox(20, imageView, label)
          root.setAlignment(Pos.CENTER)
          val view = new View(root) {
            override protected def updateAppBar(appBar: AppBar): Unit = { appBar.setTitleText("My Gluon Application") }
          }
          fab.showOn(view)
          view
        }
        foo()
      }
    )
  }
  override def start(stage: Stage): Unit = { appManager.start(stage) }
  private def postInit(scene: Scene): Unit = {
    Swatch.LIGHT_GREEN.assignTo(scene)
    scene.getStylesheets.add(classOf[Main].getResource("styles.css").toExternalForm)
    scene.getWindow.asInstanceOf[Stage].getIcons.add(new Image(classOf[Main].getResourceAsStream("/icon.png")))
    if (Platform.isDesktop) {
      val dimension2D =
        DisplayService.create.map(_.getDefaultDimensions()).orElse(new Dimension2D(640, 480))
      scene.getWindow.setWidth(dimension2D.getWidth)
      scene.getWindow.setHeight(dimension2D.getHeight)
    }
  }
}
