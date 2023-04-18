db.createUser(
    {
      user: "root",
      pwd: "rootUser",
      roles: [
        {
          role: "readWrite",
          db: "payment"
        }
      ]
    }
);
db.createCollection("test");