const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const DataSchema = new mongoose.Schema(
  {
    nome: String,
    username: String,
    password: String,
    email: String,
    ORG_id: String,
    is_active: Boolean,
  },
  {
    timestamp: true,
  }
);

DataSchema.pre("save", function (next) {
  if (!this.isModified("password")) {
    return next();
  }
  this.password = bcrypt.hashSync(this.password, 10);
  next();
});

DataSchema.pre("findOneAndUpdate", function (next) {
  console.log("to aqui");
  var password = this.getUpdate().password;
  this.getUpdate.password = bcrypt.hashSync(password, 10);

  next();
});

const org = mongoose.model("org", DataSchema);
module.exports = org;
