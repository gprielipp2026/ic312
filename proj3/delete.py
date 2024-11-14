
def delete(element, start=root) -> None:
    curNode = start
    blackNodeIsDeleted = False 

    # standard BST deletion
    while curNode != None:
        if element < curNode.data:
            # traverse left
            curNode = curNode.left
        elif element > curNode.data:
            # traverse right
            curNode = curNode.right
        else:
            # curNode.data == element
            # case: node has no children, or no left child
            if curNode.left == None:
                blackNodeIsDeleted = color(curNode) == BLACK
                parentOf(curNode).left = curNode.right
                break
            # case: node has no right child
            elif curNode.right == None:
                blackNodeIsDeleted = color(curNode) == BLACK
                parentOf(curNode).right = curNode.left
                break
            # case: node has 2 children
            else:
                successor = getSuccessor(curNode.right)
                blackNodeIsDeleted = color(curNode) == BLACK
                curNode = successor

                # remove successor from right tree
                delete(successor, curNode.right)
                break
    if blackNodeIsDeleted: 
        fixDeleteIssues(curNode)

def fixDeleteIssues(curNode):
    # cannot fix anything past the root
    if curNode == root:
        return 

    # case: Sibling is Red
    if color(sibling(curNode)) == RED:
        # swap color of parent and sibling
        parent(curNode).color = RED
        sibling(curNode).color = BLACK

        # rotate based on which child curNode is
        if sibling(curNode).isOnLeft():
            rightRotate(parent)
        else:
            leftRotate(parent)

        fixDeleteIssues(curNode)
    # case: Sibling is Black
    else:
        # sibling's children are black
        if color(sibling(curNode).right) == color(sibling(curNode).left) == BLACK:
            sibling(curNode).color = RED
            fixDeleteIssues(curNode)
        # at least one of sibling's children are red
        else:
            if sibling.left is not None and color(sibling.left) == RED:
                # left left
                if sibling(curNode).isOnLeft():
                    sibling.left.color = sibling.color
                    sibling.color = parent.color
                    rightRotate(parent)
                # right left
                else:
                    sibling.left.color = parent.color
                    rightRotate(sibling)
                    leftRotate(parent)
            else:
                # left right
                if sibling.isOnLeft():
                    sibling.right.color = parent.color
                    leftRotate(sibling)
                    rightRotate(parent)
                # right right
                else:
                    sibling.right.color = sibling.color
                    sibling.color = parent.color
                    leftRotate(parent)
