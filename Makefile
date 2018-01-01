GREEN=\033[0;32m
NC=\033[0m #No color
export CLASSPATH=/home/proje/BitirmeProjesi/Jarlar/*:/home/proje/BitirmeProjesi/src:.

clean:
	$(RM) *.class
	@echo "${GREEN}Cleanup Complete!${NC}"

server:
	javac src/jsouptests/*.java
	@echo "${GREEN}Compilation Succeeded!${NC}"
	java jsouptests.MainClass

