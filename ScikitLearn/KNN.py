from tempfile import TemporaryDirectory
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.neighbors import KNeighborsTransformer, KNeighborsClassifier
from sklearn.model_selection import GridSearchCV
from sklearn.pipeline import Pipeline

# Cargar el conjunto de datos de levadura
url = 'https://archive.ics.uci.edu/ml/machine-learning-databases/yeast/yeast.data'
column_names = ['Sequence Name', 'mcg', 'gvh', 'alm', 'mit', 'erl', 'pox', 'vac', 'nuc', 'Class']
dataset = pd.read_csv(url, names=column_names, delimiter='\s+')

# Separa las características (X) y las etiquetas (y) del dataset
X = dataset.drop(['Sequence Name', 'Class'], axis=1)
y = dataset['Class']

n_neighbors_list = [1, 2, 3, 4, 5, 6, 7, 8, 9]

# El transformer calcula el grafo de los vecinos más cercanos utilizando el número
# máximo de vecinos necesario en la búsqueda de la rejilla. El modelo clasificador filtra
# el grafo de vecinos más cercanos según se requiera por su propio parámetro n_neighbors.
graph_model = KNeighborsTransformer(n_neighbors=max(n_neighbors_list), mode="distance")
classifier_model = KNeighborsClassifier(metric="precomputed")

# Dado que usamos un directorio temporal, no necesitamos usar la clase TemporaryDirectory
# explícitamente. La limpieza se realizará automáticamente al finalizar el bloque de código.

full_model = Pipeline(
    steps=[("graph", graph_model), ("classifier", classifier_model)]
)

param_grid = {"classifier__n_neighbors": n_neighbors_list}
grid_model = GridSearchCV(full_model, param_grid)
grid_model.fit(X, y)

# Graficar los resultados de la búsqueda de hiperparámetros.
fig, axes = plt.subplots(1, 2, figsize=(8, 4))
axes[0].errorbar(
    x=n_neighbors_list,
    y=grid_model.cv_results_["mean_test_score"],
    yerr=grid_model.cv_results_["std_test_score"],
)
axes[0].set(xlabel="n_neighbors", title="Precisión de clasificación")
axes[1].errorbar(
    x=n_neighbors_list,
    y=grid_model.cv_results_["mean_fit_time"],
    yerr=grid_model.cv_results_["std_fit_time"],
    color="r",
)
axes[1].set(xlabel="n_neighbors", title="Tiempo de ajuste")
fig.tight_layout()
plt.show()
