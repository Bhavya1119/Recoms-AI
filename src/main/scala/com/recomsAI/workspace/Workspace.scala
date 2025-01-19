package com.recomsAI.workspace

 class Workspace {

  private val defaultUser           : String = "tempUser"
  private val defaultId             : String = "tempID"
  private val defaultWorkspaceID    : Option[String] = Some("sp_temp")
  private val defaultWorkspaceName  : Option[String] = Some("temp")

  var workspaceName       : Option[String] = None
  var workspaceId         : Option[String] = None
  var workspaceTimestamp  : Option[Long] = None
  var workspaceEmail      : Option[String] = None


  var userID              : String = defaultId
  var userName            : String = defaultUser

  def setWorkspace(workspaceName : Option[String] = defaultWorkspaceName,
                          workspaceId   : Option[String] = defaultWorkspaceID,
                          workspaceTimeStamp : Option[Long],
                          workspaceEmail: Option[String] = None) = {
    this.workspaceName = workspaceName
    this.workspaceId = workspaceId
    this.workspaceTimestamp = workspaceTimeStamp
    this.workspaceEmail = workspaceEmail

  }
  def setUser(userId : String = defaultId,
                userName : String = defaultUser) = {
    this.userID = userId
    this.userName = userName
  }

   def clear() : Unit = {
     this.userID = defaultUser
     this.userName = defaultUser
     this.workspaceName = None
     this.workspaceEmail = None
     this.workspaceTimestamp = None
     this.workspaceId = None
   }



}
