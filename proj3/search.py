
def find(element) -> Node:
    curNode = root

    while curNode != None:
        if element < curNode.data:
            # traverse the left subtree
            curNode = curNode.left
        elif element > curNode.data:
            # traverse the right subtree
            curNode = curNode.right
        else:
            # curNode.data == element
            return curNode 

    # if this is reached, then the element doesn't exist in the tree
    return None


