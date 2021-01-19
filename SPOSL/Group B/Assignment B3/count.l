%{
	int charcount = 0;
	int wordcount = 0;
	int linecount = 0;
%}

word [^ \t\n]+
eol \n

%%
{word} { wordcount++; charcount += yyleng; }
{eol} { charcount++; linecount++; }
. {charcount++;}
%%

int yywrap(void){
	return 1;
} 

int main(int argc, char*argv[]){
	yyin=fopen(argv[1],"r");
	yylex();
	printf("Word Count:  %d\n", wordcount);
	printf("Char Count:  %d\n", charcount); 
    	printf("Line Count:  %d\n", linecount); 
	return 0;
}



