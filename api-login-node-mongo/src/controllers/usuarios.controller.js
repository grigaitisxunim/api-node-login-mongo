const Usuario = require("../models/usuario.model");
const jwt = require("jsonwebtoken");
const secret = "mysecret";

module.exports = {
  async index(req, res) {
    const user = await Usuario.find();
    res.json(user);
  },
  async create(req, res) {
    const { nome, username, password, email, ORG_id, is_active } = req.body;
    let data = {};
    let user = await Usuario.findOne({ email });

    if (!user) {
      data = { nome, username, password, email, ORG_id, is_active };
      user = await Usuario.create(data);

      return res.status(200).json(user);
    } else {
      return res.status(500).json(user);
    }
  },
  async details(req, res) {
    const { _id } = req.params;
    const user = await Usuario.findOne({ _id });
    res.json(user);
  },
  async delete(req, res) {
    const { _id } = req.params;
    const user = await Usuario.findByIdAndDelete({ _id });
    delete user.password;
    return res.json(user);
  },
  async update(req, res) {
    const { _id, nome, username, password, email, ORG_id, is_active } =
      req.body;
    const data = { nome, username, password, email, ORG_id, is_active };
    const user = await Usuario.findOneAndUpdate({ _id }, data, { new: true });
    res.json(user);
  },
  async login(req, res) {
    const { email, password } = req.body;
    Usuario.findOne({ email: email }, function (err, user) {
      if (err) {
        console.log(err);
        res
          .status(200)
          .json({ erro: "Erro no servidor. Por favor tente mais tarde" });
      } else if (!user) {
        res.status(200).json({ status: 2, error: "Email ou senha invalida!" });
      } else {
        user.isCorrectPassword(password, async function (err, same) {
          if (err) {
            res
              .status(200)
              .json({ error: "Erro no servidor. Por favor tente mais tarde" });
          } else if (!same) {
            res
              .status(200)
              .json({ status: 2, error: "Email ou senha invalida!" });
          } else {
            const payload = { email };
            const token = jwt.sign(payload, secret, {
              expiresIn: "24h",
            });
            res.cookie("token", token, { httpOnly: true });
            res.status(200).json({
              status: 1,
              auth: true,
              token: token,
              id_user: user._id,
              user_name: user.nome,
              email: user.email,
              username: user.username,
              ORG_id: user.ORG_id,
              is_active: user.is_active,
            });
          }
        });
      }
    });
  },
};
