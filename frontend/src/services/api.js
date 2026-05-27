const API_BASE_URL =
  import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api';

export async function apiRequest(path, options = {}, token) {
  const headers = {
    'Content-Type': 'application/json',
    ...(options.headers || {}),
  };

  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...options,
    headers,
  });

  const data = await response.json().catch(() => ({}));
  if (!response.ok) {
    if (response.status === 401 || response.status === 403) {
      window.dispatchEvent(new CustomEvent("pennywise:auth-invalid"));
    }
    throw new Error(
      data.message || `Request failed with status ${response.status}`,
    );
  }
  return data;
}

// Transaction APIs
export async function getTransactions(token, page = 0, size = 10) {
  return apiRequest(
    `/transactions?page=${page}&size=${size}`,
    { method: 'GET' },
    token,
  );
}

export async function createTransaction(token, transaction) {
  return apiRequest(
    '/transactions',
    { method: 'POST', body: JSON.stringify(transaction) },
    token,
  );
}

export async function updateTransaction(token, id, transaction) {
  return apiRequest(
    `/transactions/${id}`,
    { method: 'PUT', body: JSON.stringify(transaction) },
    token,
  );
}

export async function deleteTransaction(token, id) {
  return apiRequest(`/transactions/${id}`, { method: 'DELETE' }, token);
}

export async function getTransactionsByCategory(token, category) {
  return apiRequest(
    `/transactions/category/${category}`,
    { method: 'GET' },
    token,
  );
}

// Budget APIs
export async function getBudgets(token, budgetMonth) {
  return apiRequest(
    `/budgets?budgetMonth=${budgetMonth}`,
    { method: 'GET' },
    token,
  );
}

export async function createBudget(token, budget) {
  return apiRequest(
    '/budgets',
    { method: 'POST', body: JSON.stringify(budget) },
    token,
  );
}

export async function updateBudget(token, id, budget) {
  return apiRequest(
    `/budgets/${id}`,
    { method: 'PUT', body: JSON.stringify(budget) },
    token,
  );
}

export async function deleteBudget(token, id) {
  return apiRequest(`/budgets/${id}`, { method: 'DELETE' }, token);
}

// Insight APIs
export async function getInsights(token, page = 0, size = 10) {
  return apiRequest(
    `/insights?page=${page}&size=${size}`,
    { method: 'GET' },
    token,
  );
}

export async function generateInsights(token) {
  return apiRequest('/insights/generate', { method: 'POST' }, token);
}

export async function getDashboardSummary(token) {
  return apiRequest('/dashboard/summary', { method: 'GET' }, token);
}
