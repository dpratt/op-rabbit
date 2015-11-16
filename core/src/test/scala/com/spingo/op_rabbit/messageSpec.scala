package com.spingo.op_rabbit

import org.scalatest.{FunSpec, Matchers}
import com.spingo.op_rabbit.properties._
import org.slf4j.LoggerFactory

case class Data(name: String, age: Int)

class MessageSpec extends FunSpec with Matchers {

  val logger = LoggerFactory.getLogger(this.getClass())

  describe("Message.queue") {
    it("creates a message for delivery, serializes the data, and applies the provided properties, and defaults to persistent") {
      val msg = Message.queue(
        "very payload",
        queue = "destination.queue",
        properties = List(ReplyTo("respond.here.please")))

      logger.debug("{}", msg.properties)
      msg.data should be ("very payload".getBytes)
      msg.publisher.exchangeName should be ("")
      msg.publisher.routingKey should be ("destination.queue")
      msg.properties.getDeliveryMode should be (2)
      msg.properties.getReplyTo should be ("respond.here.please")
    }
  }

  describe("Message.topic") {
    it("creates a message for delivery, and applies the provided properties, and defaults to persistent") {
      val msg = Message.topic(
        "very payload",
        routingKey = "destination.topic",
        properties = List(ReplyTo("respond.here.please")))

      logger.debug("{}", msg.properties)
      msg.properties.getDeliveryMode should be (2)
      msg.properties.getReplyTo should be ("respond.here.please")
      msg.publisher.routingKey should be ("destination.topic")
      msg.publisher.exchangeName should be (RabbitControl.topicExchangeName)
      msg.properties.getReplyTo should be ("respond.here.please")
    }
  }

  describe("Standard Message") {
    it("defaults to persistent") {
      val msg = Message("hi", Publisher.topic("very.route"), List(ReplyTo("respond.here.please")))
      msg.properties.getDeliveryMode should be (2)
      msg.properties.getReplyTo should be ("respond.here.please")
    }
  }
}
