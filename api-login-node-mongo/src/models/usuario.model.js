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

DataSchema.methods.isCorrectPassword = function (password, callback) {
  bcrypt.compare(password, this.password, function (err, same) {
    if (err) {
      callback(err);
    } else {
      callback(err, same);
    }
  });
};

const Usuario = mongoose.model("Usuarios", DataSchema);
module.exports = Usuario;
