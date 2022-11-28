#include <iostream>

struct Node
{
	int value; // �������� � �������
	int index; // ������ � �������
	bool isActive; // ����. ���������� ��������� �� ������� � ��������� ��� �������
};

// ��������� ���������� �� ������� � ����������� ��� ���� O(log2n)
void Replay(Node* tree, int i)
{
	/*
	���������� ��������� ����������.��������� ��� ����������
	������, ������� ��� � ������������ ����.
	*/
	if (i % 2 == 0)
		tree[(i - 1) / 2] = tree[i - 1];  // �������� ����� ����
	else
		tree[(i - 1) / 2] = tree[i + 1]; // �������� ������ ����

	/*
	���������� �� �����, � ������� �������� �������
	������ ��� ����������� �� ������� �����
	*/
	i = (i - 1) / 2;

	while (i > 0)
	{
		int j;
		// ���������� ��������� � ������ ��� ����� ����?
		if (i % 2 == 0)
			j = i - 1;
		else
			j = i + 1;

		//��������� ���� ���������.
		if (!tree[i].isActive || !tree[j].isActive)
			if (tree[i].isActive)
				tree[(i - 1) / 2] = tree[i];
			else
				tree[(i - 1) / 2] = tree[j];
		else // ���������� ������������. ���������� �������� � ������������ ����.
			if (tree[i].value < tree[j].value)
				tree[(i - 1) / 2] = tree[i];
			else
				tree[(i - 1) / 2] = tree[j];

		// ������� � ���������� ����� ������������. ����������� �� ������� ���� (������������ �������).
		i = (i - 1) / 2;
	}
	// ����� ���������� ��������. �������� ��������� � ����� ������.
}

int GetWinner(Node* tree)
{
	if (!tree)
		return INT_MAX;
	else
		return tree[0].value;
}
Node* Initialize(int a[], int n)//���������� ���������� ������ O(n).
{
	int levels = std::ceil(std::log2(n));// ������ ������ ������.
										 // � ������ �������� ���������� �������
										 // ���������� �� ��������
	int treesize; // ������ ������.
	int loadindex; // �������� �������, ��� �������� ��������� �������.

	// ����� �������� ������� �������: ������ ��� ��������
	if (n % 2 == 0)
	{
		//���������� ������ � �������� ������� ��� ������ ���������� ����������
		treesize = 2 * n - 1;
		loadindex = n - 1;
	}
	else
	{
		// ��� ��������
		treesize = 2 * n + 1;
		loadindex = n;
	}

	// ��������� ������ ��� ������
	Node* tree = new Node[treesize];

	//��������
	int i = 0;
	int j = 0;

	// ��������� �������� �������.
	for (i = loadindex; i < treesize; i++)
	{
		Node item; // ������� ���� ������ � ��������� ���.
		item.index = i;

		if (j < n)
		{
			item.isActive = false;
			item.value = a[j++];
		}
		else // � ������, ���� ������ ������� ��������.
			item.isActive = false;

		tree[i] = item; // �������� �������� ���� � ������.
	}

	i = loadindex;

	// ����������� ������� ����������.
	while (i > 0)
	{
		j = i;

		while (j < 2 * i)
		{
			// ��������� �������� ��������� � ��������� ���������� �� ������� ����.
			if (!tree[j + 1].isActive || tree[j].value < tree[j + 1].value)
				tree[(j - 1) / 2] = tree[j];
			else
				tree[(j - 1) / 2] = tree[j + 1];

			j += 2; // ������� � ��������� ���� ����������.
		}

		levels--;
		i = std::pow(2, levels) - 1; // ������� �� ���� ������� ����.
	}

	return tree;
}

void TournamentSort(int a[], int n)
{
	//�������� � ���������� ������.
	Node* tree = Initialize(a, n);
	// �������������� �������
	for (int i = 0; i < n - 1; i++)
	{
		a[i] = tree[0].value;
		// ��������� ���������� �������� �������, ��� �����������
		tree[tree[0].index].isActive = false;
		// ����������� ��� �������� ����������
		Replay(tree, tree[0].index); 
	}
	//�������������� ���������� ��������.
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