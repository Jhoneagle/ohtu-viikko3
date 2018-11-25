/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.query;

import java.util.Stack;
import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author Johneagle
 */
public class QueryBuilder {
    Stack<Matcher> parts;
    
    public QueryBuilder() {
        this.parts = new Stack<>();
    }
    
    public Matcher build() {
        Matcher[] matchers = new Matcher[parts.size()];
        int i = 0;
        
        while(!parts.empty()) {
            matchers[i] = parts.pop();
            i++;
        }
        
        return new All(matchers);
    }
    
    public QueryBuilder playsIn(String joukkue) {
        parts.push(new PlaysIn(joukkue));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int amount, String what) {
        parts.push(new HasAtLeast(amount, what));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int amount, String what) {
        parts.push(new HasFewerThan(amount, what));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        parts.push(new Or(matchers));
        return this;
    }
    
    public QueryBuilder and(Matcher... matchers) {
        parts.push(new And(matchers));
        return this;
    }
    
    public QueryBuilder not(Matcher... matchers) {
        parts.push(new Not(matchers));
        return this;
    }
}
