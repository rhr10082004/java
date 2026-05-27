import { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { getTransactions, createTransaction } from '../services/api';

const CATEGORIES = [
  'Food',
  'Shopping',
  'Travel',
  'Bills',
  'Entertainment',
  'Health',
  'Education',
  'Other',
];

export function TransactionsPage() {
  const { token } = useAuth();
  const [loading, setLoading] = useState(false);
  const [transactions, setTransactions] = useState([]);
  const [form, setForm] = useState({
    category: 'Food',
    amount: '',
    paymentMethod: '',
    transactionDate: '',
    note: '',
  });
  const [error, setError] = useState('');

  async function load() {
    setLoading(true);
    setError('');
    try {
      const res = await getTransactions(token, 0, 50);
      const items = res.content || res || [];
      setTransactions(items);
    } catch (err) {
      setError(err.message || 'Failed to load transactions');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    if (token) load();
  }, [token]);

  async function handleAdd(e) {
    e.preventDefault();
    setError('');
    try {
      const payload = {
        category: form.category,
        amount: parseFloat(form.amount),
        paymentMethod: form.paymentMethod,
        transactionDate: form.transactionDate,
        note: form.note,
      };
      await createTransaction(token, payload);
      setForm({
        category: 'Food',
        amount: '',
        paymentMethod: '',
        transactionDate: '',
        note: '',
      });
      await load();
    } catch (err) {
      setError(err.message || 'Failed to create transaction');
    }
  }

  return (
    <section className="space-y-4">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold">Transactions</h1>
      </div>

      <div className="rounded-xl bg-app-card p-5 shadow-soft">
        <form
          className="grid grid-cols-1 md:grid-cols-4 gap-3 mb-4"
          onSubmit={handleAdd}
        >
          <select
            value={form.category}
            onChange={(e) =>
              setForm((f) => ({ ...f, category: e.target.value }))
            }
            className="bg-slate-800 rounded-lg p-3 border border-slate-700"
          >
            {CATEGORIES.map((category) => (
              <option
                key={category}
                value={category}
              >
                {category}
              </option>
            ))}
          </select>
          <input
            placeholder="Amount"
            value={form.amount}
            onChange={(e) => setForm((f) => ({ ...f, amount: e.target.value }))}
            className="bg-slate-800 rounded-lg p-3 border border-slate-700"
          />
          <input
            placeholder="Payment Method"
            value={form.paymentMethod}
            onChange={(e) =>
              setForm((f) => ({ ...f, paymentMethod: e.target.value }))
            }
            className="bg-slate-800 rounded-lg p-3 border border-slate-700"
          />
          <input
            type="date"
            placeholder="Date"
            value={form.transactionDate}
            onChange={(e) =>
              setForm((f) => ({ ...f, transactionDate: e.target.value }))
            }
            className="bg-slate-800 rounded-lg p-3 border border-slate-700"
          />
          <input
            placeholder="Note"
            value={form.note}
            onChange={(e) => setForm((f) => ({ ...f, note: e.target.value }))}
            className="bg-slate-800 rounded-lg p-3 border border-slate-700 md:col-span-3"
          />
          <div className="md:col-span-1">
            <button
              type="submit"
              className="w-full bg-app-primary rounded-lg p-3 font-semibold"
            >
              Add
            </button>
          </div>
        </form>

        {error ? <p className="text-app-danger text-sm">{error}</p> : null}

        <div className="overflow-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="text-app-muted text-left">
                <th className="pb-2">Category</th>
                <th className="pb-2">Amount</th>
                <th className="pb-2">Date</th>
                <th className="pb-2">Method</th>
                <th className="pb-2">Note</th>
              </tr>
            </thead>
            <tbody>
              {loading ? (
                <tr>
                  <td
                    colSpan={5}
                    className="py-4"
                  >
                    Loading...
                  </td>
                </tr>
              ) : transactions.length === 0 ? (
                <tr>
                  <td
                    colSpan={5}
                    className="py-4"
                  >
                    No transactions yet.
                  </td>
                </tr>
              ) : (
                transactions.map((t) => (
                  <tr
                    key={t.id}
                    className="border-t border-slate-700/70"
                  >
                    <td className="py-2">{t.category}</td>
                    <td className="py-2">{t.amount}</td>
                    <td className="py-2">{t.transactionDate}</td>
                    <td className="py-2">{t.paymentMethod}</td>
                    <td className="py-2">{t.note}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </section>
  );
}
