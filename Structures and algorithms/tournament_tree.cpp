#include <iostream>

struct Node
{
	int value; // значение в массиве
	int index; // индекс в массиве
	bool isActive; // флаг. Обозначает участвует ли элемент в операциях над деревом
};

// выбывание победителя из турнира и переигровка без него O(log2n)
void Replay(Node* tree, int i)
{
	/*
	Определить соперника победителя.позволить ему продолжить
	турнир, копируя его в родительский узел.
	*/
	if (i % 2 == 0)
		tree[(i - 1) / 2] = tree[i - 1];  // соперник левый узел
	else
		tree[(i - 1) / 2] = tree[i + 1]; // соперник правый узел

	/*
	Переиграть те матчи, в которых принимал участие
	только что исключенный из турнира игрок
	*/
	i = (i - 1) / 2;

	while (i > 0)
	{
		int j;
		// Соперником находится в правом или левом узле?
		if (i % 2 == 0)
			j = i - 1;
		else
			j = i + 1;

		//Проверяем флаг соперника.
		if (!tree[i].isActive || !tree[j].isActive)
			if (tree[i].isActive)
				tree[(i - 1) / 2] = tree[i];
			else
				tree[(i - 1) / 2] = tree[j];
		else // Устроиваем соревнование. Победителя копируем в родительский узел.
			if (tree[i].value < tree[j].value)
				tree[(i - 1) / 2] = tree[i];
			else
				tree[(i - 1) / 2] = tree[j];

		// Перейти к следующему кругу соревнования. Поднимаемся на уровень выше (родительский уровень).
		i = (i - 1) / 2;
	}
	// Новый победитель объявлен. Значение находится в корне дерева.
}

int GetWinner(Node* tree)
{
	if (!tree)
		return INT_MAX;
	else
		return tree[0].value;
}
Node* Initialize(int a[], int n)//построение турнирного дерева O(n).
{
	int levels = std::ceil(std::log2(n));// узнаем высоту дерева.
										 // в случае нецелого показателя степени
										 // округление до большего
	int treesize; // размер дерева.
	int loadindex; // листовой уровень, где хранятся участники турнира.

	// какое значение размера массива: четное или нечетное
	if (n % 2 == 0)
	{
		//определяем размер и листовой уровень при четном количестве участников
		treesize = 2 * n - 1;
		loadindex = n - 1;
	}
	else
	{
		// при нечетном
		treesize = 2 * n + 1;
		loadindex = n;
	}

	// выдедялем память под дерево
	Node* tree = new Node[treesize];

	//счетчики
	int i = 0;
	int j = 0;

	// заполняем листовой уровень.
	for (i = loadindex; i < treesize; i++)
	{
		Node item; // создаем узел дерева и заполняем его.
		item.index = i;

		if (j < n)
		{
			item.isActive = false;
			item.value = a[j++];
		}
		else // в случае, если размер массива нечетный.
			item.isActive = false;

		tree[i] = item; // помещаем листовой узел в дерево.
	}

	i = loadindex;

	// определение первого победителя.
	while (i > 0)
	{
		j = i;

		while (j < 2 * i)
		{
			// сравнение листовых элементов и помещение победителя на уровень выше.
			if (!tree[j + 1].isActive || tree[j].value < tree[j + 1].value)
				tree[(j - 1) / 2] = tree[j];
			else
				tree[(j - 1) / 2] = tree[j + 1];

			j += 2; // переход к следующей паре участников.
		}

		levels--;
		i = std::pow(2, levels) - 1; // переход на один уровень выше.
	}

	return tree;
}

void TournamentSort(int a[], int n)
{
	//создание и заполнение дерева.
	Node* tree = Initialize(a, n);
	// упорядочивание массива
	for (int i = 0; i < n - 1; i++)
	{
		a[i] = tree[0].value;
		// помечение победителя прошлого турнира, как неактивного
		tree[tree[0].index].isActive = false;
		// переигровка без прошлого победителя
		Replay(tree, tree[0].index); 
	}
	//упорядочивание последнего элемента.
	a[n - 1] = tree[0].value;
}

void printArr(int a[], int n)
{
	std::cout << "Arr\n";
	for (int i = 0; i < n; ++i)
	{
		std::cout << a[i] << " ";
	}
	std::cout << "\n";
}

int main()
{
	int arr[] = { 100, 1, 25, 77, 15, 20, 200, 11}; 
	int sizearr = 8;

	printArr(arr, sizearr);
	TournamentSort(arr, sizearr);
	printArr(arr, sizearr);

	return 0;
}