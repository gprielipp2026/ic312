
def insert(element) -> None:
    curNode = root

    while curNode != None:
        if element < curNode.data:
            # insert new Node at the bottom/leaf node
            if curNode.left == None:
                curNode.left = Node(element)
                break

            # insert into the left subtree
            curNode = curNode.left
        elif element > curNode.data:
            # insert new Node at the bottom/leaf node
            if curNode.right == None:
                curNode.right = Node(element)
                break

            # insert into the right subtree
            curNode = curNode.right
        else:
            # curNode.data == element
            # ie, element is already in the tree
            return 
   
    # at this point curNode is the parent to the newly inserted Node
    fixViolations(curNode)
