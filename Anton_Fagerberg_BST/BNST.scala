case class Node(
  var value: Int,
  var left: Option[Node] = None,
  var right: Option[Node] = None,
  var parent: Option[Node] = None
) {
  def replace(node: Node): Unit = {
    value = node.value
    left = node.left
    right = node.right
    node.left.map(_.parent = Some(this))
    node.right.map(_.parent = Some(this))
  }

  def remove(): Unit = {
    parent.foreach { parentNode =>
      if (parentNode.left.exists(_.value == value)) parentNode.left = None
      else if (parentNode.right.exists(_.value == value)) parentNode.right = None
      else throw new IllegalStateException
    }
  }

  def createRight(value: Int): Unit = {
    right = Some(Node(value, parent = Some(this)))
  }

  def createLeft(value: Int): Unit = {
    left = Some(Node(value, parent = Some(this)))
  }
}

object BNST {
  var root: Option[Node] = None

  def insert(value: Int) {
    root match {
      case None           => root = Some(Node(value))
      case Some(rootNode) => insert(value, rootNode)
    }
  }

  private def insert(value: Int, node: Node) {
    node match {
      case Node(nodeValue, Some(leftNode), _, _)   if value < nodeValue => insert(value, leftNode)
      case Node(nodeValue, None, _, _)             if value < nodeValue => node.createLeft(value)
      case Node(nodeValue, _, Some(rightNode), _)  if value > nodeValue => insert(value, rightNode)
      case Node(nodeValue, _, None, _)             if value > nodeValue => node.createRight(value)
      case _ => throw new IllegalStateException
    }
  }

  def delete(value: Int) {
    root.foreach(n => delete(value, n))
  }

  private def delete(value: Int, currentNode: Node) {
    currentNode match {
      case Node(nodeValue, None, None, None)            if nodeValue == value => root = None
      case Node(nodeValue, None, None, Some(parent))    if nodeValue == value => currentNode.remove()
      case Node(nodeValue, Some(leftNode), None, _)     if value == nodeValue => currentNode replace leftNode
      case Node(nodeValue, None, Some(rightNode), _)    if value == nodeValue => currentNode replace rightNode
      case Node(nodeValue, Some(leftNode), Some(_), _)  if value == nodeValue => delete(leftNode, currentNode)
      case Node(nodeValue, Some(leftNode), _, _)        if value < nodeValue  => delete(value, leftNode)
      case Node(nodeValue, _, Some(rightNode), _)       if value > nodeValue  => delete(value, rightNode)
      case _ => throw new IllegalStateException
    }
  }

  private def delete(currentNode: Node, deleteNode: Node) {
    currentNode match {
      case Node(_, _, Some(rn), _)    => delete(rn, deleteNode)
      case Node(_, leftNode, None, Some(parent)) => {
        if (currentNode.value < parent.value) {
          parent.left = currentNode.left
          currentNode.left.map(_.parent = Some(parent))
        } else if (currentNode.value > parent.value) {
          parent.right = currentNode.right
          currentNode.right.map(_.parent = Some(parent))
        } else {
          throw new IllegalStateException
        }

        deleteNode.value = currentNode.value
      }
    }
  }

  def print(node: Node) {
    println(s"Value: [${node.value}] Left: [${node.left.map(_.value).getOrElse("_")}] Right: [${node.right.map(_.value).getOrElse("_")}] Parent. [${node.parent.map(_.value).getOrElse("_")}]")
    node.left.foreach(print)
    node.right.foreach(print)
  }

  def preOrder: String = {
    def recTraverse(node: Node): String = {
      s"${node.value}${node.left.map(n => s" - ${recTraverse(n)}").getOrElse("")}${node.right.map(n => s" - ${recTraverse(n)}").getOrElse("")}"
    }

    s"${root.map(recTraverse).getOrElse("Empty!")}"
  }

  def postOrder: String = {
    def recTraverse(node: Node): String = {
      s"${node.left.map(n => s"${recTraverse(n)} - ").getOrElse("")}${node.right.map(n => s"${recTraverse(n)} - ").getOrElse("")}${node.value}"
    }

    s"${root.map(recTraverse).getOrElse("Empty!")}"
  }

  def inOrder: String = {
    def recTraverse(node: Node): String = {
      s"${node.left.map(n => s"${recTraverse(n)} - ").getOrElse("")}${node.value}${node.right.map(n => s" - ${recTraverse(n)}").getOrElse("")}"
    }

    s"${root.map(recTraverse).getOrElse("Empty!")}"
  }

  def main(args: Array[String]) {
    List(8, 3, 10, 1, 6, 14, 4, 7, 13).foreach(insert)
    assert(preOrder == "8 - 3 - 1 - 6 - 4 - 7 - 10 - 14 - 13", s"Expected: $preOrder")
    delete(8)
    assert(preOrder == "7 - 3 - 1 - 6 - 4 - 10 - 14 - 13", s"Expected: $preOrder")
    delete(3)
    assert(preOrder == "7 - 1 - 6 - 4 - 10 - 14 - 13", s"Expected: $preOrder")
    insert(8)
    assert(preOrder == "7 - 1 - 6 - 4 - 10 - 8 - 14 - 13", s"Expected: $preOrder")
    insert(9)
    assert(preOrder == "7 - 1 - 6 - 4 - 10 - 8 - 9 - 14 - 13", s"Expected: $preOrder")
    delete(10)
    assert(preOrder == "7 - 1 - 6 - 4 - 9 - 8 - 14 - 13", s"Expected: $preOrder")
    delete(1)
    assert(preOrder == "7 - 6 - 4 - 9 - 8 - 14 - 13", s"Expected: $preOrder")
    delete(4)
    assert(preOrder == "7 - 6 - 9 - 8 - 14 - 13", s"Expected: $preOrder")
    delete(6)
    assert(preOrder == "7 - 9 - 8 - 14 - 13", s"Expected: $preOrder")
    delete(9)
    assert(preOrder == "7 - 8 - 14 - 13", s"Expected: $preOrder")
    delete(14)
    assert(preOrder == "7 - 8 - 13", s"Expected: $preOrder")
    delete(13)
    assert(preOrder == "7 - 8", s"Expected: $preOrder")
    delete(7)
    assert(preOrder == "8", s"Expected: $preOrder")
    delete(8)
    assert(preOrder == "Empty!", s"Expected: $preOrder")
  }
}