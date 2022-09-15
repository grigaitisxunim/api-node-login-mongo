const org = require("../models/org.model");

module.exports = {
  async index(req, res) {
    const orgn = await org.find();
    res.json(orgn);
  },
  async create(req, res) {
    const { nome, password, email, ORG_id, is_active } = req.body;
    let data = {};
    let orgn = await org.findOne({ email });

    if (!orgn) {
      data = { nome, password, email, ORG_id, is_active };
      orgn = await org.create(data);

      return res.status(200).json(orgn);
    } else {
      return res.status(500).json(orgn);
    }
  },
  async details(req, res) {
    const { _id } = req.params;
    const orgn = await org.findOne({ _id });
    res.json(orgn);
  },
  async delete(req, res) {
    const { _id } = req.params;
    const orgn = await org.findByIdAndDelete({ _id });
    delete orgn.password;
    return res.json(orgn);
  },
  async update(req, res) {
    const { _id, nome, orgnname, password, email, ORG_id, is_active } =
      req.body;
    const data = { nome, orgnname, password, email, ORG_id, is_active };
    const orgn = await org.findOneAndUpdate({ _id }, data, { new: true });
    res.json(orgn);
  },
};
