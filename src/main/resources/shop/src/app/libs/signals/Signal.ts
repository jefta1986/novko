/**
 *    @desc       A TypeScript conversion of JS Signals by Miller Medeiros
 *               Released under the MIT license
 *                http://millermedeiros.github.com/js-signals/
 *
 *    @version    1.0 - 7th March 2013
 *
 *    @author    Richard Davey, TypeScript conversion
 *    @author        Miller Medeiros, JS Signals
 *    @author        Robert Penner, AS Signals
 *
 *    @url        http://www.photonstorm.com
 */

import { SignalBinding } from './SignalBinding';

/**
 * Signals can be used to handle events and event dispatching.
 * You can listen for a Signal by binding a callback / function to it.
 * This is done by using either `Signal.add` or `Signal.addOnce`.
 *
 * For example you can listen for a touch or click event from the Input Manager
 * by using its `onDown` Signal:
 *
 * `game.input.onDown.add(function() { ... });`
 *
 * Rather than inline your function, you can pass a reference:
 *
 * `game.input.onDown.add(clicked, this);`
 * `function clicked () { ... }`
 *
 * In this case the second argument (`this`) is the context in which your function should be called.
 *
 * Now every time the InputManager dispatches the `onDown` signal (or event), your function
 * will be called.
 *
 * Very often a Signal will send arguments to your function.
 * This is specific to the Signal itself.
 * If you're unsure then check the documentation, or failing that simply do:
 *
 * `Signal.add(function() { console.log(arguments); })`
 *
 * and it will log all of the arguments your function received from the Signal.
 *
 * Sprites have lots of default signals you can listen to in their Events class, such as:
 *
 * `sprite.events.onKilled`
 *
 * Which is called automatically whenever the Sprite is killed.
 * There are lots of other events, see the Events component for a list.
 *
 * As well as listening to pre-defined Signals you can also create your own:
 *
 * `var mySignal = new Signal();`
 *
 * This creates a new Signal. You can bind a callback to it:
 *
 * `mySignal.add(myCallback, this);`
 *
 * and then finally when ready you can dispatch the Signal:
 *
 * `mySignal.dispatch(your arguments);`
 *
 * And your callback will be invoked. See the dispatch method for more details.
 *
 * @class Signal
 * @name Signal
 * @author Miller Medeiros
 * @author Georgi Semerdjiev - modified
 * @constructor
 */

/* tslint:disable */
export class Signal {

    /**
     * Signals Version Number
     * @property VERSION
     * @type String
     */
    public static VERSION: string = '1.0.0';

    /**
     * If Signal should keep record of previously dispatched parameters and
     * automatically execute listener during `add()`/`addOnce()` if Signal was
     * already dispatched before.
     * @type boolean
     */
    public memorize: boolean = false;

    /**
     * If Signal is active and should broadcast events.
     * <p><strong>IMPORTANT:</strong> Setting this property during a dispatch will only affect the next dispatch,
     * if you want to stop the propagation of a signal use `halt()` instead.</p>
     * @type boolean
     */
    public active: boolean = true;

    /**
     * @property _bindings
     * @type Array
     * @private
     */
    private _bindings: SignalBinding[] = [];

    /**
     * @property _prevParams
     * @type Any
     * @private
     */
    private _prevParams: any = null;

    /**
     * @type boolean
     * @private
     */
    private _shouldPropagate: boolean = true;

    /**
     * This variable will be set to true if preventDefault() method is called during dispatching.
     * @type {boolean}
     * @private
     */
    private _defaultPrevented: boolean = false;


    /**
     * @method validateListener
     * @param {Any} listener
     * @param {Any} fnName
     */
    public validateListener(listener: any, fnName: any): void {

        if (typeof listener !== 'function') {
            throw new Error('listener is a required param of {fn}() and should be a Function.'.replace('{fn}', fnName));
        }

    }

    /**
     * Check if listener was attached to Signal.
     * @param {Function} listener
     * @param {Object} [context]
     * @return {boolean} if Signal has the specified listener.
     */
    public has(listener: any, context: any = null): boolean {

        return this._indexOfListener(listener, context) !== -1;

    }

    /**
     * Add a listener to the signal.
     * An listener is a callback with a related context and priority.
     * You can optionally provide extra arguments which will be passed to the callback after any internal parameters.
     * @param {Function} listener Signal handler function.
     * @param {Object} [listenerContext] Context on which listener will be executed (object that should represent the
     *     `this` letiable inside listener function).
     * @param {Number} [priority] The priority level of the event listener. Listeners with higher priority will be
     *     executed before listeners with lower priority. Listeners with same priority level will be executed at the
     *     same order as they were added. (default = 0)
     * @param {...any} [args=(none)] - Additional arguments to pass to the callback (listener) function. They will be
     *     appended after any arguments usually dispatched.
     * @return {SignalBinding} An Object representing the binding between the Signal and listener.
     */
    public add(listener: any, listenerContext: any = null, priority: number = 0, ...args: any[]): SignalBinding {

        this.validateListener(listener, 'add');

        return this._registerListener(listener, false, listenerContext, priority, args);
    }

    /**
     * Add listener to the signal that should be removed after first execution (will be executed only once).
     * @param {Function} listener Signal handler function.
     * @param {Object} [listenerContext] Context on which listener will be executed (object that should represent the
     *     `this` letiable inside listener function).
     * @param {Number} [priority] The priority level of the event listener. Listeners with higher priority will be
     *     executed before listeners with lower priority. Listeners with same priority level will be executed at the
     *     same order as they were added. (default = 0)
     * @param {...any} [args=(none)] - Additional arguments to pass to the callback (listener) function. They will be
     *     appended after any arguments usually dispatched.
     * @return {SignalBinding} An Object representing the binding between the Signal and listener.
     */
    public addOnce(listener: any, listenerContext: any = null, priority: number = 0, ...args: any[]): SignalBinding {

        this.validateListener(listener, 'addOnce');

        return this._registerListener(listener, true, listenerContext, priority, args);
    }

    /**
     * Remove a single listener from the dispatch queue.
     * @param {Function} listener Handler function that should be removed.
     * @param {any} [context] Execution context (since you can add the same handler multiple times if executing in a
     *     different context).
     * @return {Function} Listener handler function.
     */
    public remove(listener: any, context: any = null) {

        this.validateListener(listener, 'remove');

        let i: number = this._indexOfListener(listener, context);

        if (i !== -1) {
            this._bindings[i]._destroy(); //no reason to a SignalBinding exist if it isn't attached to a signal
            this._bindings.splice(i, 1);
        }

        return listener;

    }

    /**
     * Remove all listeners from the Signal.
     * @param {any} [context=null] - If specified only listeners for the given context will be removed.
     */
    public removeAll(context: any = null) {

        if (context === undefined) {
            context = null;
        }

        if (!this._bindings) {
            return;
        }

        let n: number = this._bindings.length;

        while (n--) {
            if (context) {
                if (this._bindings[n].context === context) {
                    this._bindings[n]._destroy();
                    this._bindings.splice(n, 1);
                }

            } else {
                this._bindings[n]._destroy();
            }
        }

        if (!context) {
            this._bindings.length = 0;
        }
    }

    /**
     * @return {number} Number of listeners attached to the Signal.
     */
    public getNumListeners(): number {

        return this._bindings ? this._bindings.length : 0;

    }

    /**
     * Stop propagation of the event, blocking the dispatch to next listeners on the queue.
     * <p><strong>IMPORTANT:</strong> should be called only during signal dispatch, calling it before/after dispatch
     * won't affect signal broadcast.</p>
     * @see Signal.prototype.disable
     */
    public halt() {

        this._shouldPropagate = false;

    }

    /**
     * Stop the default behaviour (if possible) causing this event.
     * <p><strong>IMPORTANT:</strong> should be called only during signal dispatch, calling it before/after dispatch
     * won't have any effect.</p>
     */
    public preventDefault(): void {
        this._defaultPrevented = true;
    }

    /**
     * Dispatch/Broadcast Signal to all listeners added to the queue.
     * @param {...*} [paramsArr] Parameters that should be passed to each handler.
     * @returns {boolean} true if default was not prevented.
     */
    public dispatch(...paramsArr: any[]): boolean {

        if (!this.active || !this._bindings) {
            return true;
        }

        let n: number = this._bindings.length;
        let bindings: SignalBinding[];

        if (this.memorize) {
            this._prevParams = paramsArr;
        }

        if (!n) {
            //should come after memorize
            return true;
        }

        bindings = this._bindings.slice(0); //clone array in case add/remove items during dispatch

        this._shouldPropagate = true; //in case `halt` was called before dispatch or during the previous dispatch.
        this._defaultPrevented = false;

        //execute all callbacks until end of the list or until a callback returns `false` or stops propagation
        //reverse loop since listeners with higher priority will be added at the end of the list
        do {
            n--;
        } while (bindings[n] && this._shouldPropagate && bindings[n].execute(paramsArr) !== false);

        return !this._defaultPrevented;
    }

    /**
     * Forget memorized arguments.
     * @see Signal.memorize
     */
    public forget() {

        this._prevParams = null;

    }

    /**
     * Remove all bindings from signal and destroy any reference to libs objects (destroy Signal object).
     * <p><strong>IMPORTANT:</strong> calling any method on the signal instance after calling dispose will throw
     * errors.</p>
     */
    public dispose() {

        this.removeAll();

        this._bindings.length = 0;
        this._prevParams = null;

    }

    /**
     * @return {string} String representation of the object.
     */
    public toString(): string {

        return '[Signal active:' + this.active + ' numListeners:' + this.getNumListeners() + ']';

    }

    /**
     * @param {Function} listener - Signal handler function.
     * @param {boolean} isOnce - Should the listener only be called once?
     * @param {Object} [listenerContext] - The context under which the listener is invoked.
     * @param {Number} [priority] - The priority level of the event listener. Listeners with higher priority will be
     *     executed before listeners with lower priority. Listeners with same priority level will be executed at the
     *     same order as they were added. (default = 0).
     * @param {any[]} [args=(none)] - Additional arguments to pass to the callback (listener) function. They will be
     *     appended after any arguments usually dispatched.
     * @return {SignalBinding} An Object representing the binding between the Signal and listener.
     * @private
     */
    private _registerListener(listener: any, isOnce: boolean, listenerContext: any, priority: number,
                              args?: any[]): SignalBinding {

        let prevIndex: number = this._indexOfListener(listener, listenerContext);
        let binding: SignalBinding;

        if (prevIndex !== -1) {
            binding = this._bindings[prevIndex];

            if (binding.isOnce() !== isOnce) {
                throw new Error('You cannot add' + (isOnce ? '' : 'Once') + '() then add' + (!isOnce ? '' : 'Once') + '() the same listener without removing the relationship first.');
            }
        } else {
            binding = new SignalBinding(this, listener, isOnce, listenerContext, priority, args);

            this._addBinding(binding);
        }

        if (this.memorize && this._prevParams) {
            binding.execute(this._prevParams);
        }

        return binding;

    }

    /**
     * @method _addBinding
     * @param {SignalBinding} binding
     * @private
     */
    private _addBinding(binding: SignalBinding) {

        if (!this._bindings) {
            this._bindings = [];
        }

        //simplified insertion sort

        let n: number = this._bindings.length;

        do {
            --n;
        } while (this._bindings[n] && binding.priority <= this._bindings[n].priority);

        this._bindings.splice(n + 1, 0, binding);

    }

    /**
     * @method _indexOfListener
     * @param {Function} listener - Signal handler function.
     * @param {object} context [context=null] - Signal handler function.
     * @return {number} The index of the listener within the private bindings array.
     * @private
     */
    private _indexOfListener(listener: any, context: any): number {

        if (!this._bindings) {
            return -1;
        }

        if (context === undefined) {
            context = null;
        }

        let n: number = this._bindings.length;
        let cur: SignalBinding;

        while (n--) {
            cur = this._bindings[n];

            if (cur.getListener() === listener && cur.context === context) {
                return n;
            }
        }

        return -1;

    }

}
